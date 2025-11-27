package com.example.animee.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.animee.data.retrofit.AnimeApi

@Composable
fun AnimeListItem(
    anime: AnimeApi,
    showDetails: () -> Unit
) {
    Column{
        Text("Title: ${anime.japaneseTitle}")
        Button(
            showDetails
        ) {
            Text("Vis detaljer")
        }
    }
}