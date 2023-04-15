package com.example.movies.ui.moviesInfoFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.response.Film
import com.example.movies.rest.MoviesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieInfoFragmentViewModel : ViewModel(){

    private val TAG = "checkResult"
    private val repository = MoviesRepository()

    private val _movieLiveData = MutableLiveData<Film>()
    val movieLiveData: LiveData<Film> = _movieLiveData

    fun checkIfMovieIsEmpty() {
        repository.getFilm().enqueue(object : Callback<Film> {
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                val movieResponse  = response.body()
                if (movieResponse  != null) {
                    _movieLiveData.postValue(movieResponse!!)
                } else {
                    Log.d(TAG, "Ошибка выгрузки списка фильмов")
                }
            }
            override fun onFailure(call: Call<Film>, t: Throwable) {
                Log.d(TAG, "Ошибка загрузки", t)
            }
        })
    }
}