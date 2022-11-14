package com.example.myfilms.data.room.repository

import androidx.lifecycle.LiveData
import com.example.myfilms.model.MoviesModelItem

interface MoviesRepository {
    val allMovies: LiveData<List<MoviesModelItem>>

    suspend fun insertMovie(moviesModelItem: MoviesModelItem)
    suspend fun deleteMovie(moviesModelItem: MoviesModelItem)

}
