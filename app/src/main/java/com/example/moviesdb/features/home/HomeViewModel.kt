package com.example.moviesdb.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesdb.client.api.GetMoviePagingApi
import com.example.moviesdb.client.model.MovieModel
import com.example.moviesdb.client.model.MovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieApi: GetMoviePagingApi
) : ViewModel() {

    private val _movieSearchPaging = MutableLiveData<Flow<PagingData<MovieModel>>>()
    val movieSearchPaging: LiveData<Flow<PagingData<MovieModel>>> = _movieSearchPaging

    fun getMovieTopRatedPaging(): Flow<PagingData<MovieModel>> {
        return getMovieApi.getMoviesPaging(movieType = MovieType.TOP_RATED)
            .distinctUntilChanged().cachedIn(viewModelScope)
    }

    fun getMovieNowPlayingPaging(): Flow<PagingData<MovieModel>> {
        return getMovieApi.getMoviesPaging(movieType = MovieType.NOW_PLAYING)
            .distinctUntilChanged().cachedIn(viewModelScope)
    }

    fun getMoviePopularPaging(): Flow<PagingData<MovieModel>> {
        return getMovieApi.getMoviesPaging(movieType = MovieType.POPULAR)
            .distinctUntilChanged().cachedIn(viewModelScope)
    }

    fun getMovieUpcomingPaging(): Flow<PagingData<MovieModel>> {
        return getMovieApi.getMoviesPaging(movieType = MovieType.UP_COMING)
            .distinctUntilChanged().cachedIn(viewModelScope)
    }

    fun getMoviesSearchPaging(query: String) {

        viewModelScope.launch {
            val result = getMovieApi.getMovieSearchPaging(query)
                .distinctUntilChanged().cachedIn(viewModelScope)
            _movieSearchPaging.value = result
        }
    }

}