package com.example.animee.screens.showallanime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.retrofit.AnimeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowAllAnimeViewModel : ViewModel() {


    private val _animes = MutableStateFlow<List<AnimeApi>>(
        emptyList()
    )

    val animes = _animes.asStateFlow()

    fun setAnimes() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    AnimeAPIRepository.getAllAnimes()
                }
                if (response != null) {
                    _animes.value = response.data
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _animes.value = emptyList()
            }
        }
    }

    init {
        setAnimes()
    }
}