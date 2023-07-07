package com.example.moviesdb.client.datasource

import com.example.moviesdb.client.model.MovieDetail
import javax.inject.Inject
import javax.inject.Singleton

interface MovieDetailDataSource {
    suspend fun getMovieDetail(parameter: Int): MovieDetail
}

@Singleton
class MovieDetailDataSourceImpl @Inject constructor(
    private val service: ITheMovieDBService,
) : MovieDetailDataSource {
    override suspend fun getMovieDetail(parameter: Int): MovieDetail =
        service.getMovieDetails(parameter)
}
