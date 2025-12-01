package com.example.animee.navigation

import com.example.animee.screens.animedetails.AnimeDetailsScreen
import com.example.animee.screens.animedetails.AnimeDetailsViewModel
import com.example.animee.screens.homescreen.HomeScreen
import com.example.animee.screens.homescreen.HomeViewModel
import com.example.animee.screens.showallanime.ShowAllAnimeScreen
import com.example.animee.screens.showallanime.ShowAllAnimeViewModel
import androidx.navigation.toRoute
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animee.screens.animecreate.AnimeCreateScreen
import com.example.animee.screens.animecreate.AnimeCreateViewModel
import com.example.animee.screens.animecreatedetails.AnimeCreateDetailsScreen
import com.example.animee.screens.animecreatedetails.AnimeCreateDetailsViewModel
import com.example.animee.screens.searchanime.SearchAnimeScreen
import com.example.animee.screens.searchanime.SearchAnimeViewModel

@Composable
fun AppNavigation(
    homeViewModel: HomeViewModel,
    showAllAnimeViewModel: ShowAllAnimeViewModel,
    searchAnimeViewModel: SearchAnimeViewModel,
    animeCreateViewModel: AnimeCreateViewModel,
    animeCreateDetailsViewModel: AnimeCreateDetailsViewModel,
    animeDetailsViewModel: AnimeDetailsViewModel
){
    val navController = rememberNavController()
    var activeItem by rememberSaveable{
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {

            NavigationBar() {

                NavigationBarItem(
                    selected = activeItem == 0,
                    onClick = {
                        activeItem = 0
                        navController.navigate(NavRoutes.HomeRoute)
                    },
                    label = { Text("Home") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            null
                        )
                    }
                ) // END HOME

                NavigationBarItem(
                    selected = activeItem == 1,
                    onClick = {
                        activeItem = 1
                        navController.navigate(NavRoutes.AllAnimeRoute)
                    },
                    label = { Text("All Anime") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            null
                        )
                    }
                ) // END ALL ANIME

                NavigationBarItem(
                    selected = activeItem == 2,
                    onClick = {
                        activeItem = 2
                        navController.navigate(NavRoutes.SearchAnimeRoute)
                    },
                    label = { Text("Search Anime") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            null
                        )
                    }
                ) // END SEARCH

                NavigationBarItem(
                    selected = activeItem == 3,
                    onClick = {
                        activeItem = 3
                        navController.navigate(NavRoutes.AnimeCreateRoute)
                    },
                    label = { Text("Create new Anime") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Create,
                            null
                        )
                    }
                ) // END CREATE

            } // END NAVBAR
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.HomeRoute
            ){
                composable<NavRoutes.HomeRoute> {
                    HomeScreen(homeViewModel)
                }
                composable<NavRoutes.AllAnimeRoute> {
                    ShowAllAnimeScreen(
                        showAllAnimeViewModel,
                        navController
                    )
                }
                composable<NavRoutes.SearchAnimeRoute> {
                    SearchAnimeScreen(
                        searchAnimeViewModel,

                    )
                }
                composable<NavRoutes.AnimeCreateRoute> {
                    AnimeCreateScreen(
                        animeCreateViewModel,
                        navController
                    )
                }
                composable<NavRoutes.AnimeCreateDetailsRoute> { backstackEntery ->
                    val args = backstackEntery.toRoute<NavRoutes.AnimeCreateDetailsRoute>()
                    AnimeCreateDetailsScreen(
                        animeCreateDetailsViewModel,
                        navController,
                        args.newAnimeId,

                    )
                }

                composable<NavRoutes.AnimeDetailsRoute> { backstackEntery ->
                    val args = backstackEntery.toRoute<NavRoutes.AnimeDetailsRoute>()
                    AnimeDetailsScreen(
                        animeDetailsViewModel,
                        navController,
                        args.animeId
                    )
                }
            }
        }
    }
}