package com.example.animee.screens.animecreate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.animee.components.AnimeItem
import com.example.animee.data.UiAnime





@Composable

fun AnimeCreateScreen(animeCreateViewModel: AnimeCreateViewModel) {
    val animes by animeCreateViewModel.animes.collectAsState()

    var title by remember { mutableStateOf("") }

    val userAnimes = animes.filter { it.isCustom }
    val apiAnimes = animes.filter { !it.isCustom }

    Column {
        Text("Your Animes (${userAnimes.size})")

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Name of anime") }
        )

        Button(
            onClick = {
                if (title.isNotEmpty()) {
                    val newAnime = UiAnime(id = 0, title = title, isCustom = true)
                    animeCreateViewModel.insertAnime(newAnime)
                    title = ""
                }
            }
        ) {
            Text("Add anime")
        }

        LazyColumn {
            if (userAnimes.isNotEmpty()) {
                item { Text("Your Animes (${userAnimes.size})") }
                items(userAnimes) { AnimeItem(it) }
            } else {
                item { Text("You have created 0 animes") }
            }

            if (apiAnimes.isNotEmpty()) {
                item { Text("API Animes") }
                items(apiAnimes) { AnimeItem(it) }
            }
        }


        // Separator
        if (apiAnimes.isNotEmpty()) {
            Text("API Animes")
            LazyColumn {
                items(apiAnimes) { anime ->
                    AnimeItem(anime)
                }
            }
        }
    }
}
