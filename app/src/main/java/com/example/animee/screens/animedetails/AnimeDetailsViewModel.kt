package com.example.animee.screens.animedetails

import androidx.lifecycle.ViewModel
import com.example.animee.data.retrofit.AnimeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AnimeDetailsViewModel() : ViewModel() {

    private val _animeDetail = MutableStateFlow<AnimeApi?>(
        null
    )

    val anime = _animeDetail.asStateFlow()

//    fun setAnime(animeId: Int){
//        viewModelScope.launch {
//            // Trenger en getId fra repo + try catch?
//            _animeDetail.value = AnimeAPIRepository.getAnimeById(animeId)
//        }
//    }
}