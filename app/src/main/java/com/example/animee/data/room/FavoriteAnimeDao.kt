package com.example.animee.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteAnimeDao {

    @Query("SELECT * FROM favorite_anime")
    suspend fun getAllFavoriteAnimes() : List<FavoriteAnime>



  @Query("DELETE FROM favorite_anime WHERE id = :id")
  suspend fun deleteFavoriteAnime(id: Int)
    @Insert(onConflict = OnConflictStrategy.REPLACE) // overskriver mulige problemer med ider
    suspend fun insertFavoriteAnime(favoriteAnime: FavoriteAnime) : Long
}