package com.example.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.networking.ApiClient
import com.example.myapplication.networking.PokemonApi
import com.example.myapplication.networking.RawPokemonData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel : ViewModel() {

    var pokemonData = MutableLiveData<RawPokemonData>()

    val pokemonService: PokemonApi

    init {
        val retrofit = ApiClient()
        pokemonService = retrofit.getClient().create(PokemonApi::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            val call = pokemonService.getPokemonData()
            val response = withContext(Dispatchers.IO) {
                call.execute()
            }
            if (response.isSuccessful) {
                pokemonData.setValue(response.body())
                Log.d("Igor", "$pokemonData")
            } else {
                TODO("Implement error handling")
            }


        }

    }

}