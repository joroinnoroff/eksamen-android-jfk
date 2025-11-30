package com.example.animee.screens.animedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.animee.components.AnimeItem
import com.example.animee.screens.favorites.FavoritesViewModel

@Composable
fun AnimeDetailsScreen(
    animeDetailsViewModel: AnimeDetailsViewModel,
    navController: NavController,
    animeId: Int
) {



    val favoritesViewModel:  FavoritesViewModel = viewModel()
    val favorites = favoritesViewModel.favoriteIds.collectAsState()

    val anime = animeDetailsViewModel.anime.collectAsState()

    LaunchedEffect(animeId) {
        animeDetailsViewModel.setAnimeDetails(animeId)
    }

    Column() {
        Text(
            "Anime detail screen",
            style = MaterialTheme.typography.headlineLarge
            )


        LazyColumn() {
            item {  anime.value?.let {
                AnimeItem(it,
                    goBack = { navController.popBackStack() },
                    isFavorited = favorites.value.contains(animeId),
                    onFavorite = { favoritesViewModel.toggleFavorite(anime.value!!) }
                )
            } }
        }
    }
}