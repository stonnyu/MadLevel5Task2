package com.example.madlevel5task1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel5task1.dao.GameDao
import com.example.madlevel5task1.database.BacklogRoomDatabase
import com.example.madlevel5task1.model.Game

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = BacklogRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getBacklog(): LiveData<List<Game?>> {
        return gameDao.getBacklog()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

}
