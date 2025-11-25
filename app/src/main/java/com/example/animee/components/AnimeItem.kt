package com.example.animee.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.animee.data.UiAnime


@Composable
fun AnimeItem(anime: UiAnime){
    Column() {
        Text(anime.title)
    }
}