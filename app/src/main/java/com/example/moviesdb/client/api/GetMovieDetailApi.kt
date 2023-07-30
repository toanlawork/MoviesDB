package com.example.moviesdb.client.api

import com.example.moviesdb.client.model.MovieDetail
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMovieDetailApi @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val movieDetailRepository: MovieDetailRepository
) : UseCase<Int, MovieDetail>(dispatcher) {
    override suspend fun execute(parameters: Int): MovieDetail {
        return movieDetailRepository.getMovieDetail(parameters)
    }

}