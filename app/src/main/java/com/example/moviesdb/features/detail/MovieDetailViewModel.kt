package com.example.moviesdb.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesdb.client.api.GetCastUseCase
import com.example.moviesdb.client.api.GetDetailPagingApi
import com.example.moviesdb.client.api.GetReviewUseCase
import com.example.moviesdb.client.api.data
import com.example.moviesdb.client.api.succeeded
import com.example.moviesdb.client.model.Cast
import com.example.moviesdb.client.model.Genre
import com.example.moviesdb.client.model.MovieDetail
import com.example.moviesdb.client.model.ReviewResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetDetailPagingApi,
    private val getReviewUseCase: GetReviewUseCase,
    private val getCast: GetCastUseCase
) : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> = _movieDetail

    private val _reviewPaging = MutableLiveData<ReviewResponse>()
    val reviewPaging: LiveData<ReviewResponse> = _reviewPaging

    private val _cast = MutableLiveData<List<Cast>?>()
    val cast: LiveData<List<Cast>?> = _cast

    fun getReviewsPaging(movieId: Int) {
        viewModelScope.launch {
            val result = getReviewUseCase(parameters = movieId)
            _reviewPaging.value = result.data
        }
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val result = getMovieDetailUseCase(movieId)
            if (result.succeeded) {
                _movieDetail.value = result.data
            }
        }
    }

    fun getCastDetail(id: Int) {
        viewModelScope.launch {
            val result = getCast(id)
            if (result.succeeded) {
                _cast.value = result.data
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