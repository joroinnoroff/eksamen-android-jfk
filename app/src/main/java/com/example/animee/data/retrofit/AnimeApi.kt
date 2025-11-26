package com.example.animee.data.retrofit

import com.google.gson.annotations.SerializedName


data class AnimeApi(
    val mal_id: Int,
    @SerializedName("title_english")
    val englishTitle: String,
    @SerializedName("title_japanese")
    val japaneseTitle: String,
    val score: Int,
    val popularity: Int,
    val synopsis: String,
    val image: Images
)

data class Images(
    val webp: ImagesData
)

data class ImagesData(
    @SerializedName("image_url")
    val image: String
)


