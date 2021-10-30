package edu.alkemy.alkemychallengeandroidkotlin.Api.Implementation

import edu.alkemy.alkemychallengeandroidkotlin.Api.ApiMovies
import edu.alkemy.alkemychallengeandroidkotlin.Model.API_Response.ResponsePelicula
import edu.alkemy.alkemychallengeandroidkotlin.Model.API_Response.ResponsePeliculasPopulares
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiMoviesImp {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
    }

    fun getPeliculasPopulares(): Call<ResponsePeliculasPopulares>
    {
        return getRetrofit().create(ApiMovies::class.java).getPeliculasPopulares()
    }

    fun getPelicula(id: Int): Call<ResponsePelicula>
    {
        return getRetrofit().create(ApiMovies::class.java).getPelicula(id)
    }
}