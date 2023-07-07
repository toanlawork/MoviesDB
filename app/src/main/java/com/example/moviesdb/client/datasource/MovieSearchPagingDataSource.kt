package com.example.moviesdb.client.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesdb.client.model.MovieModel
import com.example.moviesdb.client.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MovieSearchPagingDataSource(
    private val service: ITheMovieDBService,
    private val query: String,
) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val page = params.key ?: 1
            withContext(Dispatchers.IO) {
                val data = service.getMovieSearch(query = query)

                data.results.forEach { movie ->
                    launch {
                        if (movie.id == null) return@launch

                        val movieDetail = service.getMovieDetails(movie.id)
                        movie.runtime = movieDetail.runtime
                        movie.genres = movieDetail.genres
                    }
                }
                delay(1000)
                toLoadResult(data, page)
            }
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
