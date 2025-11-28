package com.example.animee.screens.animedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.retrofit.AnimeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeDetailsViewModel() : ViewModel() {

    private val _animeDetail = MutableStateFlow<AnimeApi?>(
        null
    )



    val anime = _animeDetail.asStateFlow()



}