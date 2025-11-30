package com.example.animee.screens.animedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.retrofit.AnimeApi
import com.example.animee.data.room.AnimeDbRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeDetailsViewModel() : ViewModel() {

    private val _animeDetail = MutableStateFlow<AnimeApi?>(
        null
    )
    val anime = _animeDetail.asStateFlow()
    //favorite state
    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun setAnimeDetails(id: Int) {
        viewModelScope.launch {
           val animeId = AnimeAPIRepository.getAnimeById(id)
            _animeDetail.value = animeId
            checkIfFavorite(id)

        }
    }

    private suspend fun checkIfFavorite(id: Int){
        val favorites = AnimeDbRepository.getAllFavoriteAnimes()
        _isFavorite.value = favorites.any{ it.id == id}
    }

    fun addToFavorites(){
        viewModelScope.launch {
            val anime = _animeDetail.value ?: return@launch
            if ( _isFavorite.value) {
                AnimeDbRepository.deleteFavoriteAnimeById(anime.id)
            } else {
                AnimeDbRepository.addFavoriteAnimeFromApi(anime)
            }
            _isFavorite.value = !_isFavorite.value

        }
    }

}