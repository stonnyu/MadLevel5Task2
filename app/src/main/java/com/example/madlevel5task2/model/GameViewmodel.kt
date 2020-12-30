package com.example.madlevel5task1.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task1.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository =  GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val game = gameRepository.getBacklog()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun updateGame(title: String, platform: String) {

        //if there is an existing game, take that id to update it instead of adding a new one
        val newGame = Game(
            id = game.value?.id,
            title = title,
            date = Date(),
            platform = platform
        )

        if(isGameValid(newGame)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.updateBacklog(newGame)
                }
                success.value = true
            }
        }
    }

    private fun isGameValid(game: Game): Boolean {
        return when {
            game.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }
}
