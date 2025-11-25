package com.example.animee.data.retrofit


data class ApiAnime(
    val mal_id: Int,
    val title: String
)

data class AnimeResponse (
    val data : List<ApiAnime>
)