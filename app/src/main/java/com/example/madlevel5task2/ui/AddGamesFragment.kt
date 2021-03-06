package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task1.model.Game
import com.example.madlevel5task1.repository.GameRepository
import com.example.madlevel5task2.R
import kotlinx.android.synthetic.main.fragment_add_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGamesFragment : Fragment() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameRepository = GameRepository(requireContext())

        fabSave.setOnClickListener {
            saveGame()
            findNavController().navigate(R.id.action_addGamesFragment_to_gamesFragment)
        }

    }

    private fun dateInputFormat(): Date {
        val dateString: String = tilAddDateDay.editText?.text.toString() +
        tilAddDateMonth.editText?.text.toString() +
        tilAddDateYear.editText?.text.toString()

        return SimpleDateFormat("dMyyyy").parse(dateString)
    }

    private fun saveGame() {
        val game = Game(
                title = tilAddTitle.editText?.text.toString(),
                platform = tilAddPlatform.editText?.text.toString(),
                date = dateInputFormat()
        )
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }
    }
}