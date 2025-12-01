package com.example.animee.screens.searchanime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animee.components.AnimeItem
import com.example.animee.screens.favorites.FavoritesViewModel

@Composable
fun SearchAnimeScreen(searchAnimeViewModel: SearchAnimeViewModel) {

    val error = searchAnimeViewModel.error.collectAsState()
    val anime = searchAnimeViewModel.anime.collectAsState()
    var id by remember { mutableStateOf("")
    }
    val favoritesViewModel:  FavoritesViewModel = viewModel()
    val favorites = favoritesViewModel.favoriteAnimes.collectAsState()

    LazyColumn (
        modifier = Modifier
            .padding(36.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("Search anime")

            TextField(
                value = id,
                onValueChange = { id = it },
                label = { Text("Id") }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 44.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    OutlinedButton(
                        onClick = {
                            val idParsed = id.toIntOrNull()
                            when {
                                id.isBlank() -> searchAnimeViewModel.setError("Please provid an Id")
                                idParsed == null -> searchAnimeViewModel.setError("Id must be a number")
                                else -> searchAnimeViewModel.setAnime(idParsed)
                            }
                        }
                    ) {
                        Text("Search")
                    }
                    anime.value?.let {
                        OutlinedButton(
                            onClick = {
                                id = ""
                                searchAnimeViewModel.clear()
                            }
                        ) {
                            Text("Clear search")
                        }
                    }
                }
            }

            error.value?.let {
                Text(
                    text = it,
                    color = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }

            anime.value?.let {
                AnimeItem(it,

                    //matcher favoriter mot anime id
                    isFavorited = favorites.value.any {fav -> fav.id == it.id},
                    onFavorite = { favoritesViewModel.toggleFavorite(it) },

                )
            } ?: Text("Search to find anime")
        }
    }
}