package com.example.animee.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.example.animee.data.retrofit.AnimeApi


@Composable
fun AnimeItem(
    anime: AnimeApi,

    isFavorited: Boolean,
    onFavorite: (() -> Unit) ? = null
){
    Column {


        Text(anime.englishTitle)
        Text(anime.japaneseTitle)
        AsyncImage(
            model = anime.images.webp.image,
            contentDescription = anime.englishTitle
        )
    //add to favorites
         onFavorite?.let {
             androidx.compose.material3.IconButton(onClick = it) {
                 androidx.compose.material3.Icon(
                     imageVector =  if (isFavorited)
                         androidx.compose.material.icons.Icons.Filled.Favorite
                     else
                         androidx.compose.material.icons.Icons.Outlined.FavoriteBorder,
                         contentDescription = "Favorite"

                 )
             }
         }
        Text(anime.synopsis)
        Text(anime.score.toString())

    }
}