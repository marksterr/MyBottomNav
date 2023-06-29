package com.example.mybottomnavapp.data.remote

import com.example.mybottomnavapp.data.model.anime.AnimeModel
import com.example.mybottomnavapp.data.model.drink.DrinkModel
import com.example.mybottomnavapp.data.model.rickmorty.RickModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiCall {

    @GET(ApiDetails.END_POINT)
    suspend fun getRandomAnime(): AnimeModel

    @GET(ApiDetails.RICK_END_POINT)
    suspend fun getRickCharacter(@Path("id") id: Int): RickModel

    @GET(ApiDetails.DRINK_END_POINT)
    suspend fun getDrink(@Query("s") drinkType: String): DrinkModel
}