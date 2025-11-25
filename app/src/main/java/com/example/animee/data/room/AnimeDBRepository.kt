package com.example.animee.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room

object AnimeDBRepository {
    private lateinit var _appDatabase: AppDatabase
    private val _animeDao by lazy {_appDatabase.animeDao()}


    fun initalizeDatabase(context: Context) {
        _appDatabase = Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "anime-databse"
        ).build()
    }



    suspend fun getAllAnime() : List<DbAnime>{
        try {
            Log.i("getallAnime", "Getting anime from API")
            return _animeDao.getAll()
        } catch(e : Exception ) {

            Log.i("fetch aniime db", "No animes from the db to fetch")
            return emptyList()
        }
    }


    suspend fun insertAnime(newAnime: DbAnime): Long {
        try {
            return _animeDao.insertAnime(newAnime)
        } catch (e: Exception) {
            return  -1L
        }
    }
}