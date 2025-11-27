package com.example.animee.screens.animecreatedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.room.AnimeDbRepository
import com.example.animee.data.room.NewAnime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeCreateDetailsViewModel : ViewModel() {

    private val _newAnimeDetails = MutableStateFlow<NewAnime?>(
        null
    )

    val newAnime = _newAnimeDetails.asStateFlow()

//    fun setNewAnime(newAnimeId: Int) {
//        viewModelScope.launch {
//            _newAnimeDetails.value = AnimeDbRepository.getnewAnimeOnId(newAnimeId)
//        }
//    }

    fun deleteNewAnime(newAnimeId: Int) {
        viewModelScope.launch {
            AnimeDbRepository.deleteNewAnimeById(newAnimeId)
        }
    }

}