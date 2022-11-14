package com.example.myfilms.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myfilms.REALIZATION
import com.example.myfilms.model.MoviesModelItem

class FavoriteFragmentViewModel: ViewModel() {


    fun getAllMovies(): LiveData<List<MoviesModelItem>>{
        return REALIZATION.allMovies
    }

}