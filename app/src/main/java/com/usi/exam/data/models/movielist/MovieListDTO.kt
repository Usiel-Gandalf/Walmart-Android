package com.usi.exam.data.models.movielist

data class MovieListDTO(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)