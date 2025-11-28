package com.example.animee.screens.animecreatedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.animee.components.NewAnimeItem

@Composable
fun AnimeCreateDetailsScreen(
    animeCreateDetailsViewModel: AnimeCreateDetailsViewModel,
    navController: NavController,
    newAnimeId: Int
) {
    val newAnime = animeCreateDetailsViewModel.newAnime.collectAsState()

    LaunchedEffect(Unit) {
        animeCreateDetailsViewModel.setNewAnime(newAnimeId)
    }

    Column() {
        Text("New anime details screen")
        newAnime.value?.let { anime ->
            NewAnimeItem(
                anime,
                deleteAnime = {
                    animeCreateDetailsViewModel.deleteNewAnime(newAnimeId, onDone = {
                        navController.popBackStack()

                    })

                },
                editAnime = {}
            )
        }
    }
}