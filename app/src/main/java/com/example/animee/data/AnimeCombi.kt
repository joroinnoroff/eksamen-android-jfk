package com.example.animee.data

import com.example.animee.data.retrofit.ApiAnime
import com.example.animee.data.room.DbAnime

fun ApiAnime.toUiAnime(): UiAnime = UiAnime(
    id = this.mal_id,
    title = this.title,
    isCustom = false
)

fun DbAnime.toUiAnime(): UiAnime = UiAnime(
    id = this.id,
    title = this.title,
    isCustom = true
)


fun UiAnime.toDbAnime(): DbAnime = DbAnime(
    id = this.id,
    title = this.title
)