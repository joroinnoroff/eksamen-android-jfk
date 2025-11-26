package com.example.animee.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.example.animee.data.retrofit.AnimeApi


@Composable
fun AnimeItem(anime: AnimeApi){
    Column {
        Text(anime.englishTitle)
        Text(anime.japaneseTitle)
        AsyncImage(
            model = anime.images.webp.image,
            contentDescription = anime.englishTitle
        )
        Text(anime.synopsis)
        Text(anime.score.toString())
    }
}