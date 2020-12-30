package com.example.madlevel5task1.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.madlevel5task1.model.Game

@Dao
interface GameDao {

    @Insert
    suspend fun insertGame(game: Game)

    @Query("SELECT * FROM gameTable LIMIT 1")
    fun getBacklog(): LiveData<Game?>

    @Update
    suspend fun updateGame(game: Game)

}
