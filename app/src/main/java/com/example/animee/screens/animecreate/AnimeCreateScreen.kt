package com.example.animee.screens.animecreate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.animee.components.NewAnimeListItem
import com.example.animee.data.room.NewAnime
import com.example.animee.navigation.NavRoutes


@Composable

fun AnimeCreateScreen(
    animeCreateViewModel: AnimeCreateViewModel,
    navController: NavController
    ) {
    val newAnimes = animeCreateViewModel.newAnimes.collectAsState()

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    Column() {
        Text("Ny anime",
            style = MaterialTheme.typography.headlineLarge)
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )
        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    animeCreateViewModel.insertNewAnime(
                        NewAnime(title = title, description = description)
                    )
                }
            }
        ) {
            Text("Legg til ny anime")
        }

        if (newAnimes.value.count() > 0) {
            LazyColumn {
                items(newAnimes.value) { anime->
                    NewAnimeListItem(
                        anime,
                        editAnime = {
                            navController.navigate(NavRoutes.AnimeCreateDetailsRoute(anime.id))
                        }
                    )
                }
            }
        } else {
            Text("Ingen nye animer i databasen")
        }
    }
}

