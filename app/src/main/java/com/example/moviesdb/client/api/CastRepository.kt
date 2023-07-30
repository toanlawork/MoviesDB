package com.example.moviesdb.client.api

import com.example.moviesdb.client.model.Cast
import javax.inject.Inject
import javax.inject.Singleton

interface CastRepository {
    suspend fun getCast(parameter: Int): List<Cast>?
}

@Singleton
class CastRepositoryIml @Inject constructor(
    private val castDataSource: CastDataSource,
) : CastRepository {
    override suspend fun getCast(parameter: Int): List<Cast>? = castDataSource.getCast(parameter)
}
