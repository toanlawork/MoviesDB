package com.example.moviesdb.client.api

import androidx.paging.PagingData
import com.example.moviesdb.client.model.MovieModel
import com.example.moviesdb.client.model.MovieType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviePagingApi @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    fun getMoviesPaging(movieType: MovieType): Flow<PagingData<MovieModel>> =
        movieRepository.getMoviePaging(movieType = movieType)
}
