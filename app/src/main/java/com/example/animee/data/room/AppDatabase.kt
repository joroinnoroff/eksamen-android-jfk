package com.example.animee.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteAnime::class, NewAnime::class],
    version = 2,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase(){
    abstract fun newAnimeDao() : NewAnimeDao
    abstract fun favoriteAnimeDao() : FavoriteAnimeDao
}