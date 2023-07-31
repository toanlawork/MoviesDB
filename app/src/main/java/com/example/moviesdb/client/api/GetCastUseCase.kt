package com.example.moviesdb.client.api


import com.example.moviesdb.client.model.Cast
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCastUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: MovieRepository
) : UseCase<Int, List<Cast>?>(dispatcher) {
    override suspend fun execute(parameters: Int): List<Cast>? {
        return repository.getCast(parameters)
    }

}