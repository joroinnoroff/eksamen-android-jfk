package com.example.animee.screens.animecreatedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animee.components.NewAnimeItem

@Composable
fun AnimeCreateDetailsScreen(
    animeCreateDetailsViewModel: AnimeCreateDetailsViewModel,
    navController: NavController,
    newAnimeId: Int
) {
    val newAnime = animeCreateDetailsViewModel.newAnime.collectAsState()

    LaunchedEffect(newAnimeId) {
        animeCreateDetailsViewModel.setNewAnime(newAnimeId)
    }

    Column(
        modifier = Modifier.padding(36.dp)
    ) {
        Text("New anime details screen",
            style = MaterialTheme.typography.headlineLarge)
        newAnime.value?.let { anime ->
            NewAnimeItem(
                anime,
                deleteAnime = {
                    animeCreateDetailsViewModel.deleteNewAnime(newAnimeId, onDone = {
                        navController.popBackStack()

                    })

                },
                editAnime = {updateAnime ->
                    animeCreateDetailsViewModel.updateNewAnime(updateAnime)
                    navController.popBackStack()
                }
            )
        }
    }
}