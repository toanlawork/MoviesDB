package com.example.moviesdb.features.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesdb.Constant
import com.example.moviesdb.R
import com.example.moviesdb.client.model.Genre
import com.example.moviesdb.features.customview.NetworkImage
import com.example.moviesdb.ui.theme.dimensions
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun MovieDetailScreen(id: Int, navController: NavHostController) {
    val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()

    val lazyMovieDetail = movieDetailViewModel.movieDetail.observeAsState()
    movieDetailViewModel.getMovieDetail(id)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.back_ground)),
    ) {
        val (headerView, imageHeaderDetail, imageDetail, voteStar, title, releaseDate, divider1, runTime, divider2, genres, movieDetailTabs) = createRefs()

        lazyMovieDetail.value?.let { movieDetail ->
            headerDetail(
                navController = navController,
                modifier = Modifier
                    .constrainAs(headerView) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth(),
            )

            Box(
                modifier = Modifier
                    .constrainAs(imageHeaderDetail) {
                        top.linkTo(headerView.bottom)
                    }
                    .aspectRatio(2F)
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = dimensions()._16Dp,
                            bottomEnd = dimensions()._16Dp,
                        ),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                NetworkImage(
                    url = Constant.POSTER_BASE_URL + movieDetail.posterPath,
                    modifier = Modifier
                        .fillMaxSize(),
                )
            }

            Card(
                modifier = Modifier
                    .constrainAs(imageDetail) {
                        start.linkTo(parent.start)
                        top.linkTo(imageHeaderDetail.bottom)
                        bottom.linkTo(imageHeaderDetail.bottom)
                    }
                    .padding(start = dimensions()._30Dp)
                    .height(dimensions()._120Dp)
                    .width(dimensions()._95Dp)
                    .clip(RoundedCornerShape(dimensions()._16Dp)),
                backgroundColor = MaterialTheme.colors.surface,
                elevation = dimensions()._4Dp,
            ) {
                NetworkImage(url = Constant.POSTER_BASE_URL + movieDetail.posterPath)
            }

            TextIconDetail(
                icon = ImageVector.vectorResource(R.drawable.ic_vote_star),
                text = movieDetail.voteAverage ?: Constant.EMPTY_STRING,
                modifier = Modifier
                    .constrainAs(voteStar) {
                        end.linkTo(imageHeaderDetail.end)
                        bottom.linkTo(imageHeaderDetail.bottom)
                    }
                    .padding(end = dimensions()._8Dp, bottom = dimensions()._8Dp),
            )

            Text(
                text = movieDetail.title ?: Constant.EMPTY_STRING,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Left,
                fontSize = dimensions()._16Sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(imageDetail.end)
                        top.linkTo(imageHeaderDetail.bottom)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(
                        start = dimensions()._12Dp,
                        top = dimensions()._12Dp,
                        end = dimensions()._12Dp,
                    ),
            )

            TextIconDetail(
                icon = ImageVector.vectorResource(R.drawable.ic_calendar),
                text = movieDetail.releaseDate ?: Constant.EMPTY_STRING,
                modifier = Modifier
                    .constrainAs(releaseDate) {
                        start.linkTo(parent.start)
                        top.linkTo(imageDetail.bottom)
                        end.linkTo(divider1.start)
                    }
                    .padding(
                        start = dimensions()._50Dp,
                        top = dimensions()._16Dp,
                        end = dimensions()._8Dp,
                    ),
            )

            Box(
                modifier = Modifier
                    .constrainAs(divider1) {
                        start.linkTo(releaseDate.end)
                        top.linkTo(releaseDate.top)
                        bottom.linkTo(releaseDate.bottom)
                        end.linkTo(runTime.start)
                    }
                    .background(color = colorResource(R.color.color_696974))
                    .width(dimensions()._1Dp),
            )

            TextIconDetail(
                icon = ImageVector.vectorResource(R.drawable.ic_clock),
                text = stringResource(id = R.string.run_time, movieDetail.runtime ?: 0),
                modifier = Modifier
                    .constrainAs(runTime) {
                        start.linkTo(divider1.end)
                        top.linkTo(imageDetail.bottom)
                        end.linkTo(divider2.start)
                    }
                    .padding(
                        start = dimensions()._8Dp,
                        top = dimensions()._16Dp,
                        end = dimensions()._8Dp,
                    ),
            )

            Box(
                modifier = Modifier
                    .constrainAs(divider2) {
                        start.linkTo(runTime.end)
                        top.linkTo(releaseDate.top)
                        bottom.linkTo(releaseDate.bottom)
                        end.linkTo(genres.start)
                    }
                    .background(color = colorResource(R.color.color_696974))
                    .width(dimensions()._1Dp),
            )

            TextIconDetail(
                icon = ImageVector.vectorResource(R.drawable.ic_genre),
                text = movieDetail.genres.convertGenres(),
                modifier = Modifier
                    .constrainAs(genres) {
                        start.linkTo(divider2.end)
                        top.linkTo(imageDetail.bottom)
                        end.linkTo(parent.end)
                    }
                    .padding(
                        start = dimensions()._8Dp,
                        top = dimensions()._16Dp,
                        end = dimensions()._50Dp,
                    ),
            )

            MovieDetailTabLayout(
                movie = movieDetail,
                modifier = Modifier
                    .constrainAs(movieDetailTabs) {
                        top.linkTo(releaseDate.bottom)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
                    .padding(
                        start = dimensions()._16Dp,
                        top = dimensions()._24Dp,
                        end = dimensions()._16Dp,
                    )
                    .fillMaxWidth(),
            )
        }
    }
}
