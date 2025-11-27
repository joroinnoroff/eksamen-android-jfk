package com.example.animee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.animee.data.room.AnimeDbRepository
import com.example.animee.navigation.AppNavigation
import com.example.animee.screens.animecreate.AnimeCreateViewModel
import com.example.animee.screens.animecreatedetails.AnimeCreateDetailsViewModel
import com.example.animee.screens.animedetails.AnimeDetailsViewModel
import com.example.animee.screens.homescreen.HomeViewModel
import com.example.animee.screens.searchanime.SearchAnimeViewModel
import com.example.animee.screens.showallanime.ShowAllAnimeViewModel
import com.example.animee.ui.theme.AnimeeTheme

class MainActivity : ComponentActivity() {

    private val _homeViewModel : HomeViewModel by viewModels()
    private val _showAllAnimeViewModel : ShowAllAnimeViewModel by viewModels()
    private val _searchAnimeViewModel : SearchAnimeViewModel by viewModels()
    private val _animeCreateViewModel : AnimeCreateViewModel by viewModels()
    private val _animeCreateDetailsViewModel : AnimeCreateDetailsViewModel by viewModels()
    private val _animeDetailsViewModel : AnimeDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AnimeDbRepository.initializeDatabase(applicationContext)

        enableEdgeToEdge()
        setContent {
            AnimeeTheme {
                AppNavigation(
                    _homeViewModel,
                    _showAllAnimeViewModel,
                    _searchAnimeViewModel,
                    _animeCreateViewModel,
                    _animeCreateDetailsViewModel,
                    _animeDetailsViewModel
                )
            }
        }
    }
}
