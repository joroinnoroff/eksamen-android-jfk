package com.example.animee.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewAnime (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val isCustom: Boolean = false,
)