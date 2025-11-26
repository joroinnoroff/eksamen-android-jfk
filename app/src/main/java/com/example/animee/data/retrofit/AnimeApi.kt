package com.example.animee.data.retrofit

import android.R
import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    val data: List<AnimeApi>
)
data class AnimeApi(
    val mal_id: Int,
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
