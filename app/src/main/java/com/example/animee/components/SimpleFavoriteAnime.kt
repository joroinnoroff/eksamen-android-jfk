package com.example.animee.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animee.data.room.FavoriteAnime

@Composable
fun SimpleFavoriteAnimeItem(anime: FavoriteAnime){
    Box(modifier = Modifier
        .width(300.dp)


    ){
        AsyncImage(
            model = anime.imageUrl,
            contentDescription = anime.englishTitle,
            modifier = Modifier.fillMaxWidth()
                .height(300.dp)
        )
        Box(
            modifier = Modifier.fillMaxHeight()
                .background(Color.Black.copy(alpha = 0.8f)) // transparent overlay
                .padding(3.dp)
        ){
            Column() {
                Text(anime.englishTitle.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                 )
                Row() {
                    Text(anime.score.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White)
                }
            }
        }
    }
}
