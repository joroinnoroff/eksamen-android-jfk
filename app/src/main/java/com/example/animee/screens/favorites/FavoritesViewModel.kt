package com.example.animee.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.retrofit.AnimeApi
import com.example.animee.data.room.AnimeDbRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel: ViewModel(){
    //Bruk av Set for å holde Id unik, lagrer ikke duplikater
    //bruk av Emptyset for å starte med tomt set i start - så automatisk oppdatering
    private val _favoriteIds = MutableStateFlow<Set<Int>>(emptySet())
    val favoriteIds = _favoriteIds.asStateFlow()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            val favorites = AnimeDbRepository.getAllFavoriteAnimes()
            _favoriteIds.value = favorites.map { it.id }.toSet()
        }
    }


    fun toggleFavorite(anime: AnimeApi) {
        viewModelScope.launch {
            val currentFavorites = _favoriteIds.value

            val isFav = currentFavorites.contains(anime.id)

            if (isFav) {
                //Fjerne fra DB + oppdatere current state
                AnimeDbRepository.deleteFavoriteAnimeById(anime.id)
                _favoriteIds.value = currentFavorites - anime.id
            } else{
                //legge til Db + oppdatere current state
                AnimeDbRepository.addFavoriteAnimeFromApi(anime)
                _favoriteIds.value = currentFavorites + anime.id
            }
        }
    }
}


