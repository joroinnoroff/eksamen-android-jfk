package com.example.animee.navigation

import kotlinx.serialization.Serializable

sealed class NavRoutes {

    @Serializable
    object HomeRoute : NavRoutes()

    @Serializable
    object AllAnimeRoute : NavRoutes()

    @Serializable
    object SearchAnimeRoute : NavRoutes()

    @Serializable
    object AnimeCreateRoute : NavRoutes()

    @Serializable
    data class AnimeDetailsRoute(
        val animeId: Int
    ) : NavRoutes()
}