package com.example.moviesdb.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.moviesdb.client.api.GetMoviePagingApi
import com.example.moviesdb.client.model.MovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieApi: GetMoviePagingApi
) : ViewModel() {
    fun getMovieTopRatedPaging() =
        getMovieApi.getMoviesPaging(movieType = MovieType.TOP_RATED)
            .distinctUntilChanged().cachedIn(viewModelScope)

    fun getMovieNowPlayingPaging() =
        getMovieApi.getMoviesPaging(movieType = MovieType.NOW_PLAYING)
            .distinctUntilChanged().cachedIn(viewModelScope)

    fun getMoviePopularPaging() =
        getMovieApi.getMoviesPaging(movieType = MovieType.POPULAR)
            .distinctUntilChanged().cachedIn(viewModelScope)

    fun getMovieUpcomingPaging() =
        getMovieApi.getMoviesPaging(movieType = MovieType.UP_COMING)
            .distinctUntilChanged().cachedIn(viewModelScope)

}