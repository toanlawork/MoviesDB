package com.example.moviesdb.client.api

import com.example.moviesdb.client.model.MovieDetail
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetDetailPagingApi @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: MovieRepository
) : UseCase<Int, MovieDetail>(dispatcher) {
    override suspend fun execute(parameters: Int): MovieDetail {
        return repository.getMovieDetail(parameters)
    }

}