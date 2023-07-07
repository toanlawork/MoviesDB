package com.example.moviesdb.client.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesdb.client.model.MovieModel
import com.example.moviesdb.client.model.MovieResponse
import com.example.moviesdb.client.model.MovieType
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class MoviePagingDataSource(
    private val service: ITheMovieDBService,
    private val movieType: MovieType,
) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val page = params.key ?: 1
            val data = when (movieType) {
                MovieType.POPULAR -> service.getMoviePopular(page = page)
                MovieType.UP_COMING -> service.getMovieUpComing(page = page)
                MovieType.NOW_PLAYING -> service.getMovieNowPlaying(page = page)
                MovieType.TOP_RATED -> service.getMovieTopRated(page = page)
            }
            delay(1000)
            toLoadResult(data, page)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    private fun toLoadResult(
        data: MovieResponse,
        page: Int,
    ): LoadResult<Int, MovieModel> {
        return LoadResult.Page(
            data = data.results,
            prevKey = if (page == 1) null else page.minus(1),
            nextKey = if (page == data.totalPages) null else page + 1,
        )
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
