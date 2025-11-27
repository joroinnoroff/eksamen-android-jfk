package com.example.animee.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.animee.data.retrofit.AnimeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AnimeDbRepository {
    private lateinit var _appDatabase: AppDatabase

    private val _newAnimeDao by lazy { _appDatabase.newAnimeDao() }
    private val _favoriteAnimeDao by lazy { _appDatabase.favoriteAnimeDao() }

    fun initializeDatabase(context: Context) {
        _appDatabase = Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "Animes"
        ).build()
    }

    suspend fun getAllFavoriteAnimes() : List<FavoriteAnime>{
        try{
            Log.i("getAllFavoriteAnimeTry", "Fikk hentet Favoritter")
            return _favoriteAnimeDao.getAllFavoriteAnimes()
        }catch (e: Exception){
            Log.d("getAllFavoriteAnimeCatch", e.toString())
            return emptyList()
        }
    }

    suspend fun getAllNewAnimes() : List<NewAnime>{
        try{
            Log.i("getAllFavoriteAnimeTry", "Fikk hentet Nye Animer")
            return _newAnimeDao.getAllNewAnimes()
        }catch (e: Exception){
            Log.d("getAllNewAnimeCatch", e.toString())
            return emptyList()
        }

        suspend fun addFavoriteAnimeFromApi(anime: AnimeApi) {
            try {
                withContext(Dispatchers.IO) {//dispatcher gjør så tyngre handlinger kjører i bakgrunnen
                val favorite = FavoriteAnime( //henter animer fra api og legger det i favorite
                    id = anime.id,
                    englishTitle = anime.englishTitle,
                    japaneseTitle = anime.japaneseTitle,
                    score = anime.score,
                    imageUrl = anime.images.webp.image
                )
                _favoriteAnimeDao.insertFavoriteAnime(favorite)
            }
            }catch (e: Exception){
                Log.e("FavoriteInsert", "Kunne ikke lagre favoritt", e)
            }
        }
    }
}
