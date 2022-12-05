package com.example.movies.rest

import com.example.movies.response.FilmsList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesRepository {
    val BASE_URL = "https://s3-eu-west-1.amazonaws.com/"

    val api: MoviesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    fun getFilmsList(): Call<FilmsList> {
        return api.getFilmsList()
    }

}