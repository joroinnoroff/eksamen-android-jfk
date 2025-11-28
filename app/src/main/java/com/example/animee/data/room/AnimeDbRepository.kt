package com.example.animee.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.animee.data.retrofit.AnimeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

object AnimeDbRepository {
    private lateinit var _appDatabase: AppDatabase
    private val _newAnimeDao by lazy { _appDatabase.newAnimeDao() }
    private val _favoriteAnimeDao by lazy { _appDatabase.favoriteAnimeDao() }

    fun getAllNewAnimes(): Flow<List<NewAnime>> {
        return _newAnimeDao.getAllNewAnimes()
    }
    fun initializeDatabase(context: Context) {
        _appDatabase = Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "Animes"
        ).fallbackToDestructiveMigration()
            .build()

    }

    suspend fun getAllFavoriteAnimes() : List<FavoriteAnime>{
        try{
            Log.i("getAllFavoriteAnimeTry", "Fikk hentet Favoritter")
            return _favoriteAnimeDao.getAllFavoriteAnimes()
        }catch (e: Exception){
            Log.d("getAllFavoriteAnimeRepository", e.toString())
            return emptyList()
        }
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
                Log.e("FavoriteInsertRepository", "Kunne ikke lagre favoritt", e)
        }
    }

    suspend fun insertNewAnime(newAnime: NewAnime) : Long{
        try {
            return _newAnimeDao.insertNewAnime(newAnime)
        }catch (e: Exception){
            Log.e("insertNewAnime", "feil ved posting til db", e)
            return -1L // Databasen vil returnere -1L hvis det går galt under lagring
        }
    }

    suspend fun deleteNewAnimeById(id: Int) {
        try {
            _newAnimeDao.deleteNewAnime(id)
        } catch (e: Exception) {
            Log.e("NewAnimeRepository", "Feil ved sletting av anime med id=$id")
        }
    }

    suspend fun updateNewAnime(newAnime: NewAnime) {
        try {
            _newAnimeDao.updateNewAnime(newAnime)
        } catch (e: Exception) {
            Log.e("NewAnimeRepository", "Kunne ikke oppdatere anime", e)
        }
    }

    // Trenger en getnewAnimeOnId funkjson
    suspend fun getNewAnimeById(id: Int): NewAnime?{
        try {
            return _newAnimeDao.getNewAnimeById(id)
        } catch (e: Exception) {
            Log.e("NewAnimeRepository", "Kunne ikke hente anime med id=$id", e)
            return null
        }
    }
}
