package com.example.moviesdb.features.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.moviesdb.R
import com.example.moviesdb.client.model.MovieDetail
import com.example.moviesdb.client.model.MovieDetailTabItem
import com.example.moviesdb.ui.theme.dimensions
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun MovieDetailTabLayout(movie: MovieDetail, modifier: Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val tabRowItems = listOf(
        MovieDetailTabItem(
            text = stringResource(id = R.string.movie_detail_about_movie),
            screen = { AboutMovieScreen(text = movie.overview ?: "") },
        ),
        MovieDetailTabItem(
            text = stringResource(id = R.string.movie_detail_reviews),
            screen = { ReviewsScreen() },
        ),
        MovieDetailTabItem(
            text = stringResource(id = R.string.movie_detail_cast),
            screen = { CastScreen() },
        ),
    )

    Column(modifier = modifier) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
        ) {
            tabRowItems.forEachIndexed { index, item ->
                Tab(
                    text = { Text(text = item.text) },
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                )
            }
        }
        HorizontalPager(
            count = tabRowItems.size,
            state = pagerState,
        ) {
            tabRowItems[pagerState.currentPage].screen()
        }
    }
}

@Composable
private fun AboutMovieScreen(text: String) {
    Text(
        text = text,
        color = colorResource(id = R.color.white),
        modifier = Modifier.padding(start = dimensions()._4Dp, end = dimensions()._4Dp),
    )
}

@Composable
private fun ReviewsScreen() {
    Text(
        text = stringResource(id = R.string.about_movie),
        color = colorResource(id = R.color.white),
        modifier = Modifier.padding(start = dimensions()._4Dp, end = dimensions()._4Dp),
    )
}

@Composable
private fun CastScreen() {
    Text(
        text = stringResource(id = R.string.about_movie),
        color = colorResource(id = R.color.white),
        modifier = Modifier.padding(start = dimensions()._4Dp, end = dimensions()._4Dp),
    )
}
