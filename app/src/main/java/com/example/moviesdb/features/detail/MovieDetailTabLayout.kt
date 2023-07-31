package com.example.moviesdb.features.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.moviesdb.R
import com.example.moviesdb.client.model.Cast
import com.example.moviesdb.client.model.MovieDetail
import com.example.moviesdb.client.model.MovieDetailTabItem
import com.example.moviesdb.client.model.Review
import com.example.moviesdb.client.model.ReviewResponse
import com.example.moviesdb.features.customview.NetworkImage
import com.example.moviesdb.ui.theme.dimensions
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun MovieDetailTabLayout(
    movie: MovieDetail,
    reviewResponse: ReviewResponse?,
    cast: List<Cast>,
    modifier: Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val tabRowItems = listOf(
        MovieDetailTabItem(
            text = stringResource(id = R.string.movie_detail_about_movie),
            screen = { AboutMovieScreen(text = movie.overview ?: "") },
        ),
        MovieDetailTabItem(
            text = stringResource(id = R.string.movie_detail_reviews),
            screen = { ReviewsScreen(reviewResponse) },
        ),
        MovieDetailTabItem(
            text = stringResource(id = R.string.movie_detail_cast),
            screen = { CastScreen(cast) },
        ),
    )


    Column(modifier = modifier) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = colorResource(id = R.color.back_ground),
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(dimensions()._4Dp)
                        .padding(start = dimensions()._10Dp, end = dimensions()._10Dp)
                        .background(color = colorResource(id = R.color.color_3A3F47)),
                )
            },
        ) {
            tabRowItems.forEachIndexed { index, item ->
                Tab(
                    text = {
                        Text(
                            item.text,
                            color = if (pagerState.currentPage == index) {
                                colorResource(id = R.color.white)
                            } else {
                                colorResource(id = R.color.color_67686D)
                            },
                        )
                    },
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
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = text,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(
                start = dimensions()._4Dp,
                end = dimensions()._4Dp,
                top = dimensions()._8Dp,
            ),
            fontSize = dimensions()._12Sp,
        )
    }
}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
private fun ReviewsScreen(reviewResponse: ReviewResponse?) {

    LazyVerticalGrid(
        modifier = Modifier.padding(
            start = dimensions()._16Dp,
            end = dimensions()._16Dp,
            top = dimensions()._20Dp,
        ),
        columns = GridCells.Fixed(reviewResponse?.total_results ?: 0),
        content = {
            items(reviewResponse?.results?.size ?: 0) {
                val review = reviewResponse?.results?.get(it)
                if (review != null) {
                    ItemViewReview(data = review)
                }
            }
        }
    )

}


@Composable
private fun CastScreen(casts: List<Cast>) {
    LazyVerticalGrid(
        modifier = Modifier.padding(
            start = dimensions()._16Dp,
            end = dimensions()._16Dp,
            top = dimensions()._20Dp,
        ),
        columns = GridCells.Fixed(2),
        content = {
            casts.map { cast ->
                item {
                    CastItem(cast = cast)
                }
            }
        },
    )
}

@Composable
fun CastItem(cast: Cast) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        NetworkImage(
            url = "https://image.tmdb.org/t/p/original" + cast.profilePath,
            modifier = Modifier
                .padding(dimensions()._16Dp)
                .clip(CircleShape)
                .aspectRatio(1F),
        )
        Text(
            text = cast.name ?: "",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = dimensions()._12Sp,
        )
    }
}
