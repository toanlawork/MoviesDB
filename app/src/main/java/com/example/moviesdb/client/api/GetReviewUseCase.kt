package com.example.moviesdb.client.api

import com.example.moviesdb.client.model.MovieDetail
import com.example.moviesdb.client.model.Review
import com.example.moviesdb.client.model.ReviewResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetReviewUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: MovieRepository
) : UseCase<Int, ReviewResponse>(dispatcher) {
    override suspend fun execute(parameters: Int): ReviewResponse {
        return repository.getReview(parameters)
    }

}