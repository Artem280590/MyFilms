package com.example.myfilms.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfilms.REALIZATION
import com.example.myfilms.model.MoviesModelItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    fun insert(moviesModelItem: MoviesModelItem) =
        viewModelScope.launch (Dispatchers.IO){
            REALIZATION.insertMovie(moviesModelItem)
    }

    fun delete(moviesModelItem: MoviesModelItem) =
        viewModelScope.launch (Dispatchers.IO){
            REALIZATION.deleteMovie(moviesModelItem)
        }

}