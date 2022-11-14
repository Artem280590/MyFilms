package com.example.myfilms.screens.main

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myfilms.MAIN
import com.example.myfilms.REALIZATION
import com.example.myfilms.data.retrofit.retrofit.RetrofitRepository
import com.example.myfilms.data.room.MoviesRoomDataBase
import com.example.myfilms.data.room.repository.MoviesRepositoryRealization
import com.example.myfilms.model.MoviesModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val repository = RetrofitRepository()
    val myMovies: MutableLiveData<Response<MoviesModel>> = MutableLiveData()
    val context = application

    fun getMyMoviesRetrofit(){
        viewModelScope.launch {
            try {
                myMovies.value = repository.getMovies()
            }catch (e: Exception){
                Toast.makeText(MAIN, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun initDataBase(){
        val daoMovie = MoviesRoomDataBase.getInstance(context).getMovieDao()
        REALIZATION = MoviesRepositoryRealization(daoMovie)

    }

}