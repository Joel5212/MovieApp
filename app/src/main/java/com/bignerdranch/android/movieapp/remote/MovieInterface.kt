package com.bignerdranch.android.movieapp.remote

import com.bignerdranch.android.movieapp.data.Movie
import com.bignerdranch.android.movieapp.data.MovieResponse
import com.bignerdranch.android.movieapp.data.moviedetails.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("/")
    suspend fun getAllMovies(
        @Query(value = "s")s:String,
        @Query(value = "page")page:Int,
        @Query(value = "apiKey")apiKey:String
    ) : Response<MovieResponse>

    @GET("/")
    suspend fun getMovieDetails(
        @Query(value = "i")imdbId:String,
        @Query(value = "apiKey")apiKey:String
    ) : Response<MovieDetails>
}