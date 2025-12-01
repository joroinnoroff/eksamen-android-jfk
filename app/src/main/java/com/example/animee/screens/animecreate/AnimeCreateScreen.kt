package com.example.animee.screens.animecreate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

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
                         animeCreateViewModel.insertNewAnime(
                            NewAnime(title = title, description = description)
                        )
                        title = ""
                        description =""
                    }
                }
            }
        ) {
            Text("Legg til ny Anime")
        }

        if (newAnimes.value.count() > 0) {
            LazyColumn () {
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

