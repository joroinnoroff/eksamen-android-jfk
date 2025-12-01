package com.example.animee.data.retrofit

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    val data: List<AnimeApi>
)
data class AnimeSingleResponse(
    val data: AnimeApi
)

data class AnimeApi(
    @SerializedName("mal_id")
    val id: Int,
    @SerializedName("title")
    val englishTitle: String,
    @SerializedName("title_japanese")
    val japaneseTitle: String,
    val score: Double,
    val popularity: Int,
    val synopsis: String,
    val images: Images
)

data class Images(
    val webp: ImagesData
)

data class ImagesData(
    @SerializedName("image_url")
    val image: String
)