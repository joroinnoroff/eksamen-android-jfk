package com.example.animee.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.animee.data.room.NewAnime

@Composable
fun NewAnimeItem(newAnime: NewAnime){
    Column {
        Text(newAnime.title)
        Text(newAnime.description.toString())
    }
}