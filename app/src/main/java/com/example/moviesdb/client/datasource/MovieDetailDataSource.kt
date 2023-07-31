package com.example.moviesdb.client.datasource

import com.example.moviesdb.client.model.Cast
import com.example.moviesdb.client.model.MovieDetail
import com.example.moviesdb.client.model.ReviewResponse
import javax.inject.Inject
import javax.inject.Singleton

interface MovieDetailDataSource {
    suspend fun getMovieDetail(parameter: Int): MovieDetail

    suspend fun getReview(parameter: Int): ReviewResponse
    suspend fun getCast(parameter: Int): List<Cast>?
}

@Singleton
class MovieDetailDataSourceImpl @Inject constructor(
    private val service: ITheMovieDBService,
) : MovieDetailDataSource {
    override suspend fun getMovieDetail(parameter: Int): MovieDetail =
        service.getMovieDetails(parameter)

    override suspend fun getReview(parameter: Int): ReviewResponse {
        return service.getReviews(parameter)
    }

    override suspend fun getCast(parameter: Int): List<Cast>? {
        return service.getCredit(parameter).cast
    }
}
