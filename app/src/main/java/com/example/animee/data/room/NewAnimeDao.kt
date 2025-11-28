package com.example.animee.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface NewAnimeDao {

    @Query("SELECT * FROM new_anime")
    fun getAllNewAnimes(): Flow<List<NewAnime>>

    @Query("SELECT * FROM new_anime WHERE id = :id LIMIT 1")
    suspend fun getNewAnimeById(id: Int): NewAnime?

    @Query("DELETE FROM new_anime WHERE id = :id")
    suspend fun deleteNewAnime(id: Int)
    @Update
    suspend fun updateNewAnime(newAnime: NewAnime)
    @Insert(onConflict = OnConflictStrategy.REPLACE) // overskriver mulige problemer med ider
    suspend fun insertNewAnime(newAnime: NewAnime) : Long
}