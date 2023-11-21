package com.bignerdranch.android.movieapp.hilt

import com.bignerdranch.android.movieapp.remote.MovieInterface
import com.bignerdranch.android.movieapp.ui.details.MovieDetailsRepository
import com.bignerdranch.android.movieapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {
    @Provides
    fun provideRetrofitInterface(): MovieInterface {
        //provide life to methods, hilt will get the movie interface
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(MovieInterface::class.java)
    }

    @Provides
    fun provideRepository(movieInterface: MovieInterface) : MovieDetailsRepository{
        return MovieDetailsRepository(movieInterface)
    }

}