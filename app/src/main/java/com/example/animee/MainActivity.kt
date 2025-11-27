package com.example.animee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.animee.navigation.AppNavigation
import com.example.animee.screens.animecreate.AnimeCreateScreen
import com.example.animee.screens.animecreate.AnimeCreateViewModel
import com.example.animee.screens.animedetails.AnimeDetailsViewModel
import com.example.animee.screens.homescreen.HomeScreen
import com.example.animee.screens.homescreen.HomeViewModel
import com.example.animee.screens.searchanime.SearchAnimeViewModel
import com.example.animee.screens.showallanime.ShowAllAnimeViewModel
import com.example.animee.ui.theme.AnimeeTheme

class MainActivity : ComponentActivity() {


    private val _homeViewModel : HomeViewModel by viewModels()
    private val _showAllAnimeViewModel : ShowAllAnimeViewModel by viewModels()
    private val _searchAnimeViewModel : SearchAnimeViewModel by viewModels()
    private val _animeCreateViewModel : AnimeCreateViewModel by viewModels()
    private val _animeDetailsViewModel : AnimeDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Init


        enableEdgeToEdge()
        setContent {
            AnimeeTheme {
                AppNavigation(
                    _homeViewModel,
                    _showAllAnimeViewModel,
                    _searchAnimeViewModel,
                    _animeCreateViewModel,
                    _animeDetailsViewModel
                )
            }
        }
    }
}
