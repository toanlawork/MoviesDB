package com.example.moviesdb.features.detail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesdb.client.api.GetDetailPagingApi
import com.example.moviesdb.client.api.GetMovieDetailApi
import com.example.moviesdb.client.api.data
import com.example.moviesdb.client.api.succeeded
import com.example.moviesdb.client.model.Genre
import com.example.moviesdb.client.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetDetailPagingApi,
) : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> = _movieDetail

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val result = getMovieDetailUseCase(movieId)
            if (result.succeeded) {
                _movieDetail.value = result.data
            }
        }
    }


}


fun List<Genre>?.convertGenres(): String {
    return if (!this.isNullOrEmpty()) {
        this.firstOrNull()?.name.toString() ?: ""
    } else {
        ""
    }
}