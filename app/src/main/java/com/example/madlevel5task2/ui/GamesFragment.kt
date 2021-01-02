package com.example.madlevel5task2.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task1.model.Game
import com.example.madlevel5task1.model.GameViewModel
import com.example.madlevel5task2.R
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.android.synthetic.main.item_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GamesFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()
    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()

        fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_gamesFragment_to_addGamesFragment)
        }

        observeAddGameResult()
    }

    private fun initRv() {
        rvGames.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter
        rvGames.addItemDecoration(
                DividerItemDecoration(context,
                        DividerItemDecoration.VERTICAL)
        )
        createItemTouchHelper().attachToRecyclerView(rvGames)
    }


    private fun observeAddGameResult() {
        viewModel.game.observe(viewLifecycleOwner, Observer{ games ->
            games?.let {
                this@GamesFragment.games.clear()
                this@GamesFragment.games.addAll(games as Collection<Game>)
                this@GamesFragment.gameAdapter.notifyDataSetChanged()
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

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

//            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val reminderToDelete = games[position]

                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {

                    }
                }
            }
        }
        return ItemTouchHelper(callback)
    }
}