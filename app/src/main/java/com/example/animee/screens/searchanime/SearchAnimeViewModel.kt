package com.example.animee.screens.searchanime

import AnimeAPIRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.retrofit.AnimeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchAnimeViewModel : ViewModel() {

    private val _anime = MutableStateFlow<AnimeApi?>(
        null
    )

    val anime = _anime.asStateFlow()

//    fun setAnime(id: Int) {
//        viewModelScope.launch {
//            _anime.value = AnimeAPIRepository.getAnimeById(id)
//        }
//    }

//    init {
//        setAnime()
//    }

}