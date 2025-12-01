package com.example.animee.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animee.data.retrofit.AnimeApi

@Composable
fun SimpleAnimeItem(anime: AnimeApi){
    Box(modifier = Modifier
        .width(300.dp)
        .height(220.dp)
        .clip(RoundedCornerShape(16.dp))
        ){
            AsyncImage(
                model = anime.images.webp.image,
                contentDescription = anime.englishTitle,
                modifier = Modifier.fillMaxWidth()
                    .height(300.dp)
            )
            Box(
                modifier = Modifier.fillMaxWidth()

            ){
                Column() {
                    Text(anime.englishTitle)
                    Row() {
                        Text(anime.score.toString())
                        Text(anime.popularity.toString())
                    }
                }
        }
    }
}