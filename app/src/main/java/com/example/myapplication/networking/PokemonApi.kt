package com.example.myapplication.networking

import retrofit2.Call
import retrofit2.http.GET

interface PokemonApi {
    @GET("cards")
    fun getPokemonData(): Call<RawPokemonData>
}