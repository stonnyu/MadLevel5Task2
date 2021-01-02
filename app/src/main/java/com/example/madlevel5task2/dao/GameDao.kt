package com.example.madlevel5task1.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madlevel5task1.model.Game

@Dao
interface GameDao {

    @Insert
    suspend fun insertGame(game: Game)

    @Query("SELECT * FROM gameTable")
    fun getBacklog(): LiveData<List<Game?>>

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()

    @Update
    suspend fun updateGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

}
