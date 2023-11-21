package com.bignerdranch.android.movieapp.data



data class MovieResponse(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)