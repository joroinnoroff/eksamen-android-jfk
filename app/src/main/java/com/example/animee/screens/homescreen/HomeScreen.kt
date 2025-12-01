package com.example.animee.screens.homescreen

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    //snap states per
    val favoriteListState = rememberLazyListState()
    val animesListByScoreState = rememberLazyListState()
    val animesListByPoplularityState = rememberLazyListState()

    LazyColumn(

    ) {

        item {
            Text(  "My Favorites",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
            )

            Box(modifier = Modifier.fillMaxWidth()) {
              if (favoritesAnimes.isEmpty()){
                  //Om ingen favoriter
                  Text(
                      "No favorites yet"
                  )
              } else {
                  LazyRow(
                      //snap til center på swipe
                      state = favoriteListState,
                      flingBehavior = rememberSnapFlingBehavior(
                          lazyListState = favoriteListState
                      ),
                      horizontalArrangement = Arrangement.spacedBy(36.dp),
                      contentPadding = PaddingValues(horizontal = 36.dp),
                      modifier = Modifier.align(Alignment.Center)
                  ) {
                      items(favoritesAnimes) {anime ->
                          SimpleFavoriteAnimeItem(anime)
                      }
                  }
              }
            }
        }
        //end
        item {
            Spacer(
                modifier = Modifier.padding(top = 36.dp)
            )
        }
        item{
            Text("By Score",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, 8.dp, 16.dp)
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                LazyRow(
                    //snap til center på swipe
                    state = animesListByScoreState,
                    flingBehavior = rememberSnapFlingBehavior(
                        lazyListState = animesListByScoreState
                    ),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    items(allAnimesByScore) { anime -> SimpleAnimeItem(anime) }
                }
            }
        }
        //end

        item {
            Spacer(
                modifier = Modifier.padding(top = 26.dp)
            )
        }
        item {
            Text("By Popularity",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, 8.dp, 16.dp))

            Box(modifier = Modifier.fillMaxWidth()){
                LazyRow(
                    //state
                    state = animesListByPoplularityState,
                    flingBehavior = rememberSnapFlingBehavior(
                        lazyListState = animesListByPoplularityState
                    ),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    modifier = Modifier.align(Alignment.Center)

                ) {
                    items(allAnimesByPopularity) { anime -> SimpleAnimeItem(anime) }
                }
            }
        }

        item {
            Spacer(
                modifier = Modifier.padding(bottom = 26.dp)
            )
        }
        //end

    }
}
