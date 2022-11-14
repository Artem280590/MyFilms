package com.example.myfilms.model

data class MoviesModel(
    val page: Int,
    val results: List<MoviesModelItem>,
    val total_pages: Int,
    val total_results: Int
)