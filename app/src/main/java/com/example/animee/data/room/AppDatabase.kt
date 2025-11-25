package com.example.animee.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [DbAnime::class, NewAnime::class],
    version = 1,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao() : AnimeDao
}
