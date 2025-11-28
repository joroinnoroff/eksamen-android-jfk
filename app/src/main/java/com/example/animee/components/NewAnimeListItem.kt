package com.example.animee.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.animee.data.room.NewAnime

@Composable
fun NewAnimeListItem(
    newAnime: NewAnime,
    editAnime: () -> Unit
) {
    Column() {
        Button(
            editAnime
        ) {
            Column() {
                Text("Id: ${newAnime.id}")
                Text("Title: ${newAnime.title}")
                Text("Description: ${newAnime.description}")
            }
        }

    }
}