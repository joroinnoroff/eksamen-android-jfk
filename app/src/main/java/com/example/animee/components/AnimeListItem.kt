package com.example.animee.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.example.animee.data.retrofit.AnimeApi

@Composable
fun AnimeListItem(
    anime: AnimeApi,
    showDetails: () -> Unit
) {
    Column{
        Text("Title: ${anime.englishTitle}")
        AsyncImage(
            model = anime.images.webp.image,
            contentDescription = anime.englishTitle
        )
        Text("Score: ${anime.score}")
        Button(
            showDetails
        ) {
            Text("Vis detaljer")
        }
    }
}