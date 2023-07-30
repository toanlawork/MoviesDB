package com.example.moviesdb.client.api

import com.example.moviesdb.client.datasource.ITheMovieDBService
import com.example.moviesdb.client.model.Cast
import javax.inject.Inject
import javax.inject.Singleton

interface CastDataSource {
    suspend fun getCast(parameter: Int): List<Cast>?
}

@Singleton
class CastDataSourceImpl @Inject constructor(
    private val service: ITheMovieDBService,
) : CastDataSource {
    override suspend fun getCast(parameter: Int): List<Cast>? =
        service.getCredit(parameter).cast
}
