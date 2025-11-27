package com.example.animee.screens.animedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.animee.components.AnimeItem

@Composable
fun AnimeDetailsScreen(
    animeDetailsViewModel: AnimeDetailsViewModel,
    navController: NavController,
    animeId: Int
) {
    val anime = animeDetailsViewModel.anime.collectAsState()

//    LaunchedEffect(Unit) {
//        animeDetailsViewModel.setAnime(animeId)
//    }

    Column() {
        Text("Anime detail screen")
        anime.value?.let {
            AnimeItem(it,
                goBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}