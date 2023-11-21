package com.bignerdranch.android.movieapp.ui.details

import com.bignerdranch.android.movieapp.data.moviedetails.MovieDetails
import com.bignerdranch.android.movieapp.remote.MovieInterface
import com.bignerdranch.android.movieapp.utils.Constants
import com.bignerdranch.android.movieapp.utils.Status
import com.bignerdranch.android.movieapp.utils.Result
class MovieDetailsRepository(private val movieInterface : MovieInterface){

    suspend fun getMovieDetails(imdbId : String): Result<MovieDetails>
    {
        return try{
            val response = movieInterface.getMovieDetails(imdbId, Constants.API_KEY)
            if(response.isSuccessful)
            {
                Result(Status.SUCCESS, response.body(), null)
            }
            else
            {
                Result(Status.ERROR, null, null)
            }

        }catch (e : Exception){
                Result(Status.ERROR, null, null)
        }
    }
}