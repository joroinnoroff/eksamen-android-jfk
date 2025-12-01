package com.example.animee.screens.animecreatedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.room.AnimeDbRepository
import com.example.animee.data.room.NewAnime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeCreateDetailsViewModel : ViewModel() {

    private val _newAnimeDetails = MutableStateFlow<NewAnime?>(
        null
    )

    val newAnime = _newAnimeDetails.asStateFlow()

    fun setNewAnime(id: Int) {
        viewModelScope.launch {
            val animeFromDb = AnimeDbRepository.getNewAnimeById(id)
            _newAnimeDetails.value = animeFromDb
        }
    }

    fun updateNewAnime(updateAnime: NewAnime) {
        viewModelScope.launch(Dispatchers.IO) {
            AnimeDbRepository.updateNewAnime(updateAnime)
            _newAnimeDetails.value = updateAnime

        }
    }

    fun deleteNewAnime(newAnimeId: Int, onDone: () ->Unit) {
        viewModelScope.launch {
            AnimeDbRepository.deleteNewAnimeById(newAnimeId)
            _newAnimeDetails.value = null
            onDone()
        }
    }
}