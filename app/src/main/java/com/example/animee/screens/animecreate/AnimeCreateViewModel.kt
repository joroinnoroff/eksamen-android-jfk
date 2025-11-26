package com.example.animee.screens.animecreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeCreateViewModel: ViewModel() {

    init {
        loadAnimes()
    }

    fun loadAnimes(){
        viewModelScope.launch(Dispatchers.IO) {

          //Db anime

        }
    }
}
