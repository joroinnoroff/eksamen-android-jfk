package com.example.animee.screens.animecreate

import AnimeAPIRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animee.data.UiAnime

import com.example.animee.data.room.AnimeDBRepository

import com.example.animee.data.toDbAnime
import com.example.animee.data.toUiAnime

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeCreateViewModel: ViewModel() {
    private val _animes = MutableStateFlow<List<UiAnime>>(emptyList())
    val animes = _animes.asStateFlow()


    init {
        loadAnimes()
    }

    fun loadAnimes(){
        viewModelScope.launch(Dispatchers.IO) {

          //Db anime
            val dbList = AnimeDBRepository.getAllAnime().map {it.toUiAnime()}

            // Api anime
            val apiList = AnimeAPIRepository.getAnimesFromAPI().map { it.toUiAnime() }


            //Combi
            _animes.value = dbList + apiList

        }
        }



    fun insertAnime(newAnime: UiAnime ) {
        viewModelScope.launch (Dispatchers.IO){
            val dbAnime = newAnime.toDbAnime()
            val insertId = AnimeDBRepository.insertAnime(dbAnime)
            if (insertId != -1L) {
                val savedAnime = dbAnime.copy(id = insertId.toInt()).toUiAnime()
                _animes.value = _animes.value + savedAnime
            }
        }
    }






}
