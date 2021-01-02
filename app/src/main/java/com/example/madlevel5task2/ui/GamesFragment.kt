package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task1.model.GameViewModel
import com.example.madlevel5task2.R
import kotlinx.android.synthetic.main.fragment_add_game.*
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.android.synthetic.main.item_game.*
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GamesFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_gamesFragment_to_addGamesFragment)
        }

        observeAddNoteResult()
    }

    private fun observeAddNoteResult() {
        viewModel.game.observe(viewLifecycleOwner, Observer{ note ->
            note?.let {
                tvBacklogGameTitle?.text = it.title
                tvBacklogGamePlatform?.text = it.platform
                tvBacklogGameDate?.text = it.date.toString()
            }
        })

    }

    fun onDeleteSelection(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}