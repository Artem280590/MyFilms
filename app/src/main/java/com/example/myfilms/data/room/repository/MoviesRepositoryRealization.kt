package com.example.myfilms.data.room.repository

import androidx.lifecycle.LiveData
import com.example.myfilms.data.room.dao.MoviesDao
import com.example.myfilms.model.MoviesModelItem

class MoviesRepositoryRealization(private val moviesDao: MoviesDao): MoviesRepository {
    override val allMovies: LiveData<List<MoviesModelItem>>
        get() = moviesDao.getFavoriteMovie()

    override suspend fun insertMovie(moviesModelItem: MoviesModelItem) {
        moviesDao.insert(moviesModelItem)
    }

    override suspend fun deleteMovie(moviesModelItem: MoviesModelItem) {
        moviesDao.delete(moviesModelItem)
    }

}