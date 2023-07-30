package com.example.moviesdb.client.api

import com.example.moviesdb.client.datasource.MovieDetailDataSource
import com.example.moviesdb.client.model.MovieDetail
import javax.inject.Inject

interface MovieDetailRepository {
    suspend fun getMovieDetail(parameter: Int): MovieDetail
}

class MovieDetailRepositoryIml @Inject constructor(
    private val movieDetailDataSource: MovieDetailDataSource,
) : MovieDetailRepository {
    override suspend fun getMovieDetail(parameter: Int): MovieDetail =
        movieDetailDataSource.getMovieDetail(parameter)
}
