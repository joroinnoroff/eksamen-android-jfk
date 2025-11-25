package com.example.animee.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
@Dao
interface AnimeDao {
    @Query("SELECT * FROM DbAnime")
    suspend fun getAll(): List<DbAnime>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(newAnime: DbAnime): Long
}