package com.bignerdranch.android.movieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.bignerdranch.android.movieapp.data.Movie
import com.bignerdranch.android.movieapp.data.moviedetails.MovieDetails
import com.bignerdranch.android.movieapp.remote.MovieInterface
import com.bignerdranch.android.movieapp.ui.details.MovieDetailsRepository
import com.bignerdranch.android.movieapp.ui.movie.MoviePaging
import com.bignerdranch.android.movieapp.utils.Events
import com.bignerdranch.android.movieapp.utils.Result
import com.bignerdranch.android.movieapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieInterface: MovieInterface, private val repository: MovieDetailsRepository): ViewModel() {


    private val query = MutableLiveData<String>("")

    // our api will return as page which contains 10 list items
    // switchMap allows you to switch values in the live data
    val list = query.switchMap {query->
            Pager(PagingConfig(pageSize = 10)) {
                MoviePaging(query, movieInterface)
            }.liveData.cachedIn(viewModelScope)
        }

    fun setQuery(s:String){
        query.postValue(s)
    }

    private val _movieDetails = MutableLiveData<Events<Result<MovieDetails>>>()
    val movieDetails: LiveData<Events<Result<MovieDetails>>> = _movieDetails


    fun getMovieDetails(imdbId: String) = viewModelScope.launch {
        _movieDetails.postValue(Events(Result(Status.LOADING, null, null)))
        _movieDetails.postValue(Events(repository.getMovieDetails(imdbId)))

    }

}