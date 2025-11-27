package com.example.animee.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewAnimeDao {

    @Query("SELECT * FROM new_anime")
    suspend fun getAllNewAnimes() : List<NewAnime>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // overskriver mulige problemer med ider
    suspend fun insertNewAnime(newAnime: NewAnime) : Long
}