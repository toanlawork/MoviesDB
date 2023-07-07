package com.example.moviesdb.client.auth

import com.example.moviesdb.client.api.MovieRepository
import com.example.moviesdb.client.api.MovieRepositoryIml
import com.example.moviesdb.client.datasource.MovieDataSource
import com.example.moviesdb.client.datasource.MovieDataSourceImpl
import com.example.moviesdb.client.datasource.MovieDetailDataSource
import com.example.moviesdb.client.datasource.MovieDetailDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieModule {


    @Binds
    @Singleton
    abstract fun provideMovieDataSource(
        dataSourceImpl: MovieDataSourceImpl,
    ): MovieDataSource

    @Binds
    @Singleton
    abstract fun provideMovieDetailDataSource(
        dataSourceImpl: MovieDetailDataSourceImpl,
    ): MovieDetailDataSource

    @Binds
    @Singleton
    abstract fun provideMovieRepository(
        repo: MovieRepositoryIml,
    ): MovieRepository
}