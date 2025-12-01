package com.example.animee.screens.showallanime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animee.components.AnimeListItem
import com.example.animee.navigation.NavRoutes

@Composable
fun ShowAllAnimeScreen(
    showAllAnimeViewModel: ShowAllAnimeViewModel,
    navController: NavController
) {
    val animes = showAllAnimeViewModel.animes.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Show all screen")
        LazyColumn() {
            items(animes.value) {anime ->
                AnimeListItem(
                    anime,
                    showDetails = {
                        navController.navigate(NavRoutes.AnimeDetailsRoute(anime.id))
                    }
                )
            }
        }
    }
}