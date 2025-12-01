package com.example.animee.screens.animecreate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animee.components.NewAnimeListItem
import com.example.animee.data.room.NewAnime
import com.example.animee.navigation.NavRoutes
import kotlinx.coroutines.launch


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

    val scope = rememberCoroutineScope()

    Column(

    ) {
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



    //oppretter anime til database + id til detailsskjerm
        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    scope.launch {
                        val insertedAnime = animeCreateViewModel.insertNewAnime(NewAnime(title = title, description = description)
                        )
                        navController.navigate(NavRoutes.AnimeCreateDetailsRoute(insertedAnime.id))
                    }
                }
            }
        ) {
            Text("Legg til ny Anime")
        }

        if (newAnimes.value.count() > 0) {
            LazyColumn (




            ) {
                items(newAnimes.value,
                    key ={it.id}) { anime ->
                    val animeId = anime.id
                        NewAnimeListItem(
                            anime,
                            editAnime = {
                                navController.navigate(NavRoutes.AnimeCreateDetailsRoute(animeId))
                            }

                        ) // END NewAnimeListItem


                }
            }
        } else {
            Text("Ingen nye animer i databasen")
        }
    }
}

