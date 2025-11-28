package com.example.animee.screens.searchanime

import AnimeAPIRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.retrofit.AnimeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchAnimeViewModel : ViewModel() {

    //anime
    private val _anime = MutableStateFlow<AnimeApi?>(
        null
    )
    val anime = _anime.asStateFlow()


    //error handling
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()
    fun setAnime(id: Int) {
        viewModelScope.launch {
            val result = AnimeAPIRepository.getAnimeById(id)
            if (result != null){
                _anime.value = result
                _error.value = null
            } else {
                _anime.value = null
                _error.value = "No anime matching your search"
            }
        }
    }
    fun clear() {
        _anime.value = null
        _error.value = null
    }
    fun setError(message: String) {
        _error.value = message
    }



}