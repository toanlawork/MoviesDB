package com.example.moviesdb.client.datasource

import com.example.moviesdb.client.model.ChangesResponse
import com.example.moviesdb.client.model.MovieDetail
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

interface MovieDataSource {
    suspend fun getChanges(parameter: String): ChangesResponse
    suspend fun getMoviesDetail(parameter: List<Int?>): List<Deferred<MovieDetail>>
}

@Singleton
class MovieDataSourceImpl @Inject constructor(
    private val service: ITheMovieDBService,
) : MovieDataSource {
    override suspend fun getChanges(parameter: String): ChangesResponse =
        service.getMoviesChanges(parameter)

    override suspend fun getMoviesDetail(parameter: List<Int?>): List<Deferred<MovieDetail>> {
        return withContext(Dispatchers.IO) {
            parameter.map { id ->
                async {
                    // this will allow to run multiple tasks in parallel
                    if (id != null) {
                        try {
                            service.getMovieDetails(id)
                        } catch (ex: IOException) {
                            MovieDetail(id = id, runTime = null, genres = null)
                        } catch (ex: HttpException) {
                            MovieDetail(id = id, runTime = null, genres = null)
                        }
                    } else {
                        MovieDetail(id = null, runTime = null, genres = null)
                    }
                }
            }
        }
    }
}
