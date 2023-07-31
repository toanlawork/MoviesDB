package com.example.moviesdb.client.datasource

import com.example.moviesdb.client.model.Credit
import com.example.moviesdb.client.model.Genres
import com.example.moviesdb.client.model.MovieDetail
import com.example.moviesdb.client.model.MovieResponse
import com.example.moviesdb.client.model.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ITheMovieDBService {

    // Get movie popular paging
    @GET("movie/popular")
    suspend fun getMoviePopular(@Query("page") page: Int): MovieResponse

    // Get movie top_rated paging
    @GET("movie/top_rated")
    suspend fun getMovieTopRated(@Query("page") page: Int): MovieResponse

    // Get movie upcoming paging
    @GET("movie/upcoming")
    suspend fun getMovieUpComing(@Query("page") page: Int): MovieResponse

    // Get movie now_playing paging
    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(@Query("page") page: Int): MovieResponse

    // Get movie details
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetail

    // Search movie
    @GET("search/movie")
    suspend fun getMovieSearch(@Query("query") query: String): MovieResponse

    // Get credit
    @GET("movie/{movieId}/credits")
    suspend fun getCredit(@Path("movieId") movieId: Int): Credit


    @GET("movie/{movieId}/reviews")
    suspend fun getReviews(@Path("movieId") movieId: Int): ReviewResponse
}
