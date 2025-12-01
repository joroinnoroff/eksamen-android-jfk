package com.example.animee.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.animee.data.retrofit.AnimeApi

@Composable
fun AnimeItem(
    anime: AnimeApi,
    isFavorited: Boolean,
    onFavorite: (() -> Unit) ? = null
){
    Column {
        Text(anime.japaneseTitle,
            fontSize = 20.sp)
        Text(anime.englishTitle,
            fontSize = 20.sp)
        Box(modifier = Modifier
            .clip(RoundedCornerShape(0))
            .fillMaxWidth()
            .height(250.dp)) {
        AsyncImage(
            model = anime.images.webp.image,
            contentDescription = anime.englishTitle,
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(3000.dp)
        )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 28.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //add to favorites
            onFavorite?.let {
                IconButton(onClick = it) {
                   Icon(
                        imageVector = if (isFavorited){
                            Icons.Filled.Favorite
                        }else{
                            Icons.Outlined.FavoriteBorder
                             },
                        contentDescription = "Favorite",
                        Modifier.size(36.dp),
                       tint = Color.Red
                    )
                }
            }
            Box{
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    tint = Color(0xFFFFB300),
                    modifier = Modifier.size(36.dp)
                    )
                Text(anime.score.toString(),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Light)
                }
            }
        }
        Text(anime.synopsis)
    }
}