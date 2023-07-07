package com.example.moviesdb.client.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviesdb.client.datasource.MoviePagingDataSource
import com.example.moviesdb.client.datasource.ITheMovieDBService
import com.example.moviesdb.client.datasource.MovieDataSource
import com.example.moviesdb.client.datasource.MovieSearchPagingDataSource
import com.example.moviesdb.client.model.ChangesResponse
import com.example.moviesdb.client.model.MovieDetail
import com.example.moviesdb.client.model.MovieModel
import com.example.moviesdb.client.model.MovieType
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

interface MovieRepository {
    suspend fun getChanges(parameter: String): ChangesResponse
    fun getMoviePaging(movieType: MovieType): Flow<PagingData<MovieModel>>
    suspend fun getMoviesDetail(parameter: List<Int?>): List<Deferred<MovieDetail>>
    fun getMovieSearchPaging(parameter: String): Flow<PagingData<MovieModel>>
}

@Singleton
class MovieRepositoryIml @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val movieApiService: ITheMovieDBService,
) : MovieRepository {
    override suspend fun getChanges(parameter: String): ChangesResponse =
        movieDataSource.getChanges(parameter)

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

}
