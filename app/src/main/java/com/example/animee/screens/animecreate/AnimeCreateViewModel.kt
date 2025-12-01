package com.example.animee.screens.animecreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.room.AnimeDbRepository
import com.example.animee.data.room.NewAnime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AnimeCreateViewModel: ViewModel() {

    // Tilstand
   val newAnimes = AnimeDbRepository.getAllNewAnimes()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    // hente ut Id ved auto generert Id ved insert
    suspend fun insertNewAnime(newAnime: NewAnime): NewAnime {
       val id = AnimeDbRepository.insertNewAnime(newAnime).toInt()
        return newAnime.copy(id = id)
    }

}
