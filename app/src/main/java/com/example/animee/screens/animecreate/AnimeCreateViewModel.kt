package com.example.animee.screens.animecreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.room.AnimeDbRepository
import com.example.animee.data.room.NewAnime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeCreateViewModel: ViewModel() {

    // Tilstand
    private val _newAnimes = MutableStateFlow<List<NewAnime>>(emptyList())
    // Tilstand kun lese tilgang
    val newAnimes = _newAnimes.asStateFlow()

    fun loadAnimes(){
        viewModelScope.launch(Dispatchers.IO) {
            _newAnimes.value = AnimeDbRepository.getAllNewAnimes()
        }
    }

    // Kjører funksjonene som henter alle animene fra db
    init {
        loadAnimes()
    }

    fun insertNewAnime(newAnime: NewAnime) {
        viewModelScope.launch(Dispatchers.IO) {
            val insertedId = AnimeDbRepository.insertNewAnime(newAnime)
            if (insertedId != -1L) {
                val anime = newAnime.copy(id = insertedId.toInt())
                _newAnimes.value += anime
            }
        }
    }
}
