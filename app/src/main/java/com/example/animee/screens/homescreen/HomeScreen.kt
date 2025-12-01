package com.example.animee.screens.homescreen

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animee.components.AnimeItem
import com.example.animee.components.SimpleAnimeItem
import com.example.animee.components.SimpleFavoriteAnimeItem
import com.example.animee.screens.favorites.FavoritesViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel){


    //Sorterer baser på popularitet, score som vi henter fra apiet
    val allAnimes by homeViewModel.animes.collectAsState()
    val allAnimesByScore = allAnimes.sortedByDescending { it.score }
    val allAnimesByPopularity = allAnimes.sortedByDescending { it.popularity }

    // val favoritesAnime
    val favoritesViewModel:  FavoritesViewModel = viewModel()
    val favoritesAnimes by favoritesViewModel.favoriteAnimes.collectAsState()



    val lazyListState = rememberLazyListState()


    LazyColumn() {

        item {
            Text(  "Favorites")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(favoritesAnimes) {anime ->
                    SimpleFavoriteAnimeItem(anime)
                }
            }
        }

        item {
            LazyRow(

            ) {
                items(allAnimesByScore) { anime -> SimpleAnimeItem(anime) }
            }
        }
        item {
            LazyRow(
            ) {
                items(allAnimesByPopularity) { anime -> SimpleAnimeItem(anime) }
            }
        }

        item {

        FlowColumn() {
            allAnimes.forEach { anime ->
                SimpleAnimeItem(anime)
            }
        }
        }

    }
}
