package com.example.animee.screens.animedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
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

    LaunchedEffect(animeId) {
        animeDetailsViewModel.setAnimeDetails(animeId)
    }

    Column() {
        Text("Anime detail screen")


        LazyColumn() {
            item {  anime.value?.let {
                AnimeItem(it,
                    goBack = {
                        navController.popBackStack()
                    }
                )
            } }
        }
    }
}