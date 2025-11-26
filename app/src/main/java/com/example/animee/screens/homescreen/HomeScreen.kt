package com.example.animee.screens.homescreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.animee.components.AnimeItem

@Composable
fun HomeScreen(homeViewModel: HomeViewModel){


    val allAnimes by homeViewModel.animes.collectAsState()

    LazyColumn {
        items(allAnimes){ anime -> AnimeItem(anime)}
    }
}