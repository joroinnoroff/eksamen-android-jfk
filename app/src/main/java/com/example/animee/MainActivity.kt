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
import com.example.animee.data.room.AnimeDBRepository
import com.example.animee.screens.animecreate.AnimeCreateScreen
import com.example.animee.screens.animecreate.AnimeCreateViewModel
import com.example.animee.ui.theme.AnimeeTheme

class MainActivity : ComponentActivity() {
    private val _animeCreateViewModel : AnimeCreateViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Init
        AnimeDBRepository.initalizeDatabase(applicationContext)

        enableEdgeToEdge()
        setContent {
            AnimeeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(

                    ) {
                        AnimeCreateScreen(_animeCreateViewModel)
                    }
                }
            }
        }
    }
}
