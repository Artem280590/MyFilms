package com.example.myfilms.data.retrofit.retrofit

import com.example.myfilms.data.retrofit.api.RetrofitInstance
import com.example.myfilms.model.MoviesModel
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovies(): Response<MoviesModel>{
        return RetrofitInstance.api.getMovie()
    }
}