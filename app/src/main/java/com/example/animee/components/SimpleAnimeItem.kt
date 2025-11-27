package com.example.animee.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.example.animee.data.retrofit.AnimeApi

@Composable
fun SimpleAnimeItem(anime: AnimeApi){
    Column() {
        Text(anime.englishTitle)

        AsyncImage(
            model = anime.images.webp.image,
            contentDescription = anime.englishTitle
        )
        Row() {
            Text(anime.score.toString())
            Text(anime.popularity.toString())
        }
    }
}