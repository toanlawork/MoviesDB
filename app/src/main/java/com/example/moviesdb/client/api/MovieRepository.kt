package com.example.moviesdb.client.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviesdb.client.datasource.MoviePagingDataSource
import com.example.moviesdb.client.datasource.ITheMovieDBService
import com.example.moviesdb.client.datasource.MovieDataSource
import com.example.moviesdb.client.datasource.MovieDetailDataSource
import com.example.moviesdb.client.datasource.MovieSearchPagingDataSource
import com.example.moviesdb.client.model.Cast
import com.example.moviesdb.client.model.MovieDetail
import com.example.moviesdb.client.model.MovieModel
import com.example.moviesdb.client.model.MovieType
import com.example.moviesdb.client.model.ReviewResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

interface MovieRepository {
    fun getMoviePaging(movieType: MovieType): Flow<PagingData<MovieModel>>
    suspend fun getMoviesDetail(parameter: List<Int?>): List<Deferred<MovieDetail>>

    fun getMovieSearchPaging(parameter: String): Flow<PagingData<MovieModel>>

    suspend fun getMovieDetail(parameter: Int): MovieDetail

    suspend fun getCast(parameter: Int): List<Cast>?

    suspend fun getReview(parameter: Int): ReviewResponse
}

@Singleton
class MovieRepositoryIml @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val movieApiService: ITheMovieDBService,
    private val movieDetailDataSource: MovieDetailDataSource
) : MovieRepository {
    override fun getMoviePaging(movieType: MovieType): Flow<PagingData<MovieModel>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                MoviePagingDataSource(service = movieApiService, movieType = movieType)
            },
        ).flow.flowOn(Dispatchers.IO)

    override suspend fun getMoviesDetail(parameter: List<Int?>): List<Deferred<MovieDetail>> =
        movieDataSource.getMoviesDetail(parameter)

    override fun getMovieSearchPaging(parameter: String): Flow<PagingData<MovieModel>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                MovieSearchPagingDataSource(service = movieApiService, query = parameter)
            },
        ).flow.flowOn(Dispatchers.IO)

    override suspend fun getMovieDetail(parameter: Int): MovieDetail =
        movieDetailDataSource.getMovieDetail(parameter)

    override suspend fun getCast(parameter: Int): List<Cast>? {
        return movieDetailDataSource.getCast(parameter)
    }

    override suspend fun getReview(parameter: Int): ReviewResponse {
        return movieDetailDataSource.getReview(parameter)
    }

}
