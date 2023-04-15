package com.example.movies.ui.genresFragment

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.response.FilmsList
import com.example.movies.rest.MoviesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Duration

class GenresFragmentViewModel : ViewModel() {

    private val TAG = "checkResult"
    private val repository = MoviesRepository()

    private val _moviesListLiveData: MutableLiveData<FilmsList> = MutableLiveData()
    val moviesListLiveData: LiveData<FilmsList> get() = _moviesListLiveData

    init {
        checkIfFilmsListIsEmpty()
    }

    private fun checkIfFilmsListIsEmpty() {
        repository.getFilmsList().enqueue(object : Callback<FilmsList> {
            override fun onResponse(call: Call<FilmsList>, response: Response<FilmsList>) {
                val currentResponse = response.body()
                if (currentResponse != null) {
                    _moviesListLiveData.postValue(response.body())
                    currentResponse.films.forEach() {
                        it.onClick
                    }
                } else {
                    Log.d(TAG, "Ошибка выгрузки списка фильмов")
                }
            }

            override fun onFailure(call: Call<FilmsList>, t: Throwable) {
                Log.d(TAG, "Ошибка загрузки", t)
            }
        })
    }

}

