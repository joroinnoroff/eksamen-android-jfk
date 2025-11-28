package com.example.animee.screens.searchanime

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animee.components.AnimeItem

@Composable
fun SearchAnimeScreen(
    searchAnimeViewModel: SearchAnimeViewModel) {

    val error = searchAnimeViewModel.error.collectAsState()
    val anime = searchAnimeViewModel.anime.collectAsState()
    var id by remember { mutableStateOf("")
    }


    LazyColumn (

        modifier = Modifier.padding(34.dp)
    ) {
        item {
            Text("Search anime")

            TextField(
                value = id,
                onValueChange = { id = it },
                label = { Text("Id") }
            )

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

            error.value?.let {
                Text(
                    text = it,
                    color = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }

            anime.value?.let {
                AnimeItem(it)
            } ?: Text("Search to find anime")

        }
    }


}