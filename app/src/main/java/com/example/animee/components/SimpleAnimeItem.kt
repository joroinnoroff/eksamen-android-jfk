package com.example.animee.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    .width(250.dp),
                contentScale = ContentScale.Crop
            )

        Box(modifier = Modifier
            .matchParentSize()
            .background(Color.Black.copy(0.3f)))

        Box(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ){
            Column(modifier = Modifier
                .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween){
                Text(anime.englishTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White)
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Box{
                        Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color(0xFFFFB300),
                            modifier = Modifier.size(20.dp)

                        )
                        Text(anime.score.toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color.White)
                        }
                    }
                    Text("Popularity: " + anime.popularity.toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                }
            }
        }
   }
}