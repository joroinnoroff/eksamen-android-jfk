package com.example.animee.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_anime")
data class FavoriteAnime(
    @PrimaryKey
    val id: Int,
    val englishTitle: String?,
    val japaneseTitle: String?,
    val score: Double?,
    val imageUrl: String
)

@Entity(tableName = "new_anime")
data class NewAnime(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String?
)

