package com.example.myfilms.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myfilms.model.MoviesModelItem

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(moviesModelItem: MoviesModelItem)

    @Delete
    fun delete(moviesModelItem: MoviesModelItem)

    @Query("SELECT * from movie_table")
    fun getFavoriteMovie(): LiveData<List<MoviesModelItem>>
}