package com.example.animee.data.retrofit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

    @GET("anime")
    suspend fun getAllAnime(@Query("page") page: Int) : Response<AnimeResponse>


    @GET("anime/{id}")
    suspend fun getAnimeById(
        @Path("id") id: Int
    ) : Response<AnimeSingleResponse>



}