package com.example.animee.screens.animedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val favoritesViewModel: FavoritesViewModel = viewModel()

    val favorites = favoritesViewModel.favoriteAnimes.collectAsState()

    val anime = animeDetailsViewModel.anime.collectAsState()

    LaunchedEffect(animeId) {
        animeDetailsViewModel.setAnimeDetails(animeId)
    }

    Column(
        modifier = Modifier.padding(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        LazyColumn() {
            item {  anime.value?.let {
                AnimeItem(it,
                    //matcher favorit anime id mot anime id & toggle med viewmodel
                    isFavorited = favorites.value.any {fav -> fav.id == it.id},
                    onFavorite = { favoritesViewModel.toggleFavorite(anime.value!!) }
                    )
                }
            }
        }

    }
}