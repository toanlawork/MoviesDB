package com.example.moviesdb.features.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesdb.R
import com.example.moviesdb.features.DetailNavItem
import com.example.moviesdb.features.customview.CustomPagingGridView
import com.example.moviesdb.features.customview.MovieSearchItemView
import com.example.moviesdb.features.customview.MoviesSearchView
import com.example.moviesdb.features.home.HomeViewModel
import com.example.moviesdb.ui.theme.dimensions
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun SearchScreen(pagerState: PagerState, navController: NavHostController) {

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
            .background(colorResource(id = R.color.back_ground)),
    ) {
        val movieViewModels = hiltViewModel<HomeViewModel>()

        Text(
            text = stringResource(id = R.string.search),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(start = dimensions()._16Dp, top = dimensions()._42Dp),
            textAlign = TextAlign.Center,
            fontSize = dimensions()._18Sp,
        )
        Spacer(Modifier.height(dimensions()._24Dp))

        MoviesSearchView(onSearchMoviesClick = { query ->
            movieViewModels.getMoviesSearchPaging(query)
        })

        val lazyPagingItems =
            movieViewModels.movieSearchPaging.observeAsState().value?.collectAsLazyPagingItems()

        if (lazyPagingItems != null) {
            CustomPagingGridView(
                lazyPagingItems = lazyPagingItems,
                count = 1,
                initItemView = { index ->
                    val imageMovieUrl = lazyPagingItems[index]?.posterPath
                    val movie = lazyPagingItems[index]

                    if (imageMovieUrl != null && movie != null && movie.id != null) {
                        MovieSearchItemView(
                            movie = movie,
                            onMovieItemClick = {
                                navController.navigate("${DetailNavItem.DETAIL.route}/${movie.id}")
                            },
                        )
                    }
                },
            )
        }
    }
}