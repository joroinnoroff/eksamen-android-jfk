package com.example.animee.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.retrofit.AnimeApi
import com.example.animee.data.room.AnimeDbRepository
import com.example.animee.data.room.FavoriteAnime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel: ViewModel(){
    private val _favoriteAnimes = MutableStateFlow<List<FavoriteAnime>>(emptyList())
    val favoriteAnimes = _favoriteAnimes.asStateFlow()


    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _favoriteAnimes.value = AnimeDbRepository.  getAllFavoriteAnimes()
        }
    }

    fun toggleFavorite(anime: AnimeApi) {
        viewModelScope.launch {
            val currentFavorites = _favoriteAnimes.value.toMutableList()
            val existing = currentFavorites.find { it.id == anime.id }
            //Fjerne markert som favorit - fjerne i DB
            if ( existing != null) {
                AnimeDbRepository.deleteFavoriteAnimeById(anime.id)
                currentFavorites.remove(existing)
            } else {
                // Lagre anime som favoritt i DB
                AnimeDbRepository.addFavoriteAnimeFromApi(anime)
                //Sende hele objekt til ui
                currentFavorites.add(
                    FavoriteAnime(
                        id = anime.id,
                        englishTitle = anime.englishTitle,
                        japaneseTitle = anime.japaneseTitle,
                        score = anime.score,
                        imageUrl = anime.images.webp.image
                    )
                )
            }
            //Kalle på
            _favoriteAnimes.value = currentFavorites
        }
    }



}


