package com.example.moviesdb.features.home

import android.widget.SearchView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesdb.Constant
import com.example.moviesdb.R
import com.example.moviesdb.client.model.MovieModel
import com.example.moviesdb.client.model.MovieTabItem
import com.example.moviesdb.client.model.MovieType
import com.example.moviesdb.features.DetailNavItem
import com.example.moviesdb.features.HomeBottomItem
import com.example.moviesdb.features.customview.CustomPagingGridHorView
import com.example.moviesdb.features.customview.CustomPagingGridView
import com.example.moviesdb.features.customview.CustomTextField
import com.example.moviesdb.features.customview.NetworkImage
import com.example.moviesdb.features.search.SearchScreen
import com.example.moviesdb.ui.theme.dimensions
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun HomeScreen(pagerState: PagerState, navController: NavHostController) {

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.back_ground))
            .wrapContentSize(Alignment.TopCenter),
    ) {
        Text(
            text = stringResource(id = R.string.home_title),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(start = dimensions()._16Dp, top = dimensions()._42Dp),
            textAlign = TextAlign.Center,
            fontSize = dimensions()._18Sp,
        )
        Spacer(Modifier.height(dimensions()._24Dp))
        MovieTabContentHorizontalScreen(MovieType.TOP_RATED, navController)
        TabLayout(navController)
    }
}


@Composable
fun SearchView(
    onSearchHomeClick: (() -> Unit)? = null,
    onSearchMoviesClick: ((query: String) -> Unit)? = null,
) {
    var text by remember { mutableStateOf("") }
    val maxChar = 50
    val modifier = if (onSearchHomeClick != null) {
        Modifier
            .background(colorResource(id = R.color.color_3A3F47))
            .padding(start = dimensions()._16Dp, end = dimensions()._8Dp)
            .height(dimensions()._42Dp)
            .fillMaxWidth()
            .clickable {
                onSearchHomeClick()
            }
    } else {
        Modifier
            .background(colorResource(id = R.color.color_3A3F47))
            .padding(start = dimensions()._16Dp, end = dimensions()._8Dp)
            .height(dimensions()._42Dp)
            .fillMaxWidth()
    }

    CustomTextField(
        text = text,
        trailingIcon = {
            IconButton(onClick = {
                if (onSearchMoviesClick != null) {
                    onSearchMoviesClick(text)
                }
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_search_view),
                    contentDescription = "",
                    tint = colorResource(id = R.color.color_67686D),
                )
            }
        },
        leadingIcon = null,
        onValueChange = {
            if (it.length <= maxChar) text = it
        },
        modifierBox = Modifier
            .padding(start = dimensions()._16Dp, end = dimensions()._16Dp)
            .clip(RoundedCornerShape(dimensions()._16Dp)),
        modifier = modifier,
        fontSize = dimensions()._14Sp,
        placeholderText = stringResource(id = R.string.search),
    )
}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
private fun TabLayout(mainNavHostController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val tabRowItems = listOf(
        MovieTabItem(
            text = stringResource(id = R.string.now_playing),
            screen = { MovieTabContentScreen(MovieType.NOW_PLAYING, mainNavHostController) },
        ),
        MovieTabItem(
            text = stringResource(id = R.string.upcoming),
            screen = { MovieTabContentScreen(MovieType.UP_COMING, mainNavHostController) },
        ),
        MovieTabItem(
            text = stringResource(id = R.string.popular),
            screen = { MovieTabContentScreen(MovieType.POPULAR, mainNavHostController) },
        ),
        MovieTabItem(
            text = stringResource(id = R.string.top_rated),
            screen = { MovieTabContentScreen(MovieType.TOP_RATED, mainNavHostController) },
        ),
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        ScrollableTabRow(
            edgePadding = dimensions()._0Dp,
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
                    icon = null,
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

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
private fun MovieTabContentScreen(
    movieType: MovieType,
    mainNavHostController: NavHostController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        MoviesPagingGridView(
            movieType = movieType,
            onMovieItemClick = { movie ->
                mainNavHostController.navigate("${DetailNavItem.DETAIL.route}/${movie.id}")
            },
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
private fun MovieTabContentHorizontalScreen(
    movieType: MovieType,
    mainNavHostController: NavHostController,
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp),
    ) {
        MoviesPagingGridHorView(
            onMovieItemClick = { movie ->

                mainNavHostController.navigate("${DetailNavItem.DETAIL.route}/${movie.id}")
            },
        )
    }
}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MoviesPagingGridView(
    movieType: MovieType,
    onMovieItemClick: (MovieModel) -> Unit,
) {

    val movieViewModel = hiltViewModel<HomeViewModel>()

    val getMovie = when (movieType) {
        MovieType.POPULAR -> movieViewModel.getMoviePopularPaging()
            .collectAsLazyPagingItems()

        MovieType.UP_COMING -> movieViewModel.getMovieUpcomingPaging()
            .collectAsLazyPagingItems()

        MovieType.NOW_PLAYING -> movieViewModel.getMovieNowPlayingPaging()
            .collectAsLazyPagingItems()

        MovieType.TOP_RATED -> movieViewModel.getMovieTopRatedPaging()
            .collectAsLazyPagingItems()
    }

    CustomPagingGridView(
        lazyPagingItems = getMovie,
        count = 3,
        initItemView = { index ->
            val movie = getMovie[index]
            val imageMovieUrl = movie?.posterPath
            if (movie != null && imageMovieUrl != null) {
                Card(
                    modifier = Modifier
                        .padding(
                            start = dimensions()._7Dp,
                            end = dimensions()._7Dp,
                            bottom = dimensions()._18Dp,
                        )
                        .clip(RoundedCornerShape(dimensions()._16Dp))
                        .height(dimensions()._146Dp)
                        .width(dimensions()._100Dp),
                    backgroundColor = MaterialTheme.colors.surface,
                    elevation = dimensions()._4Dp,
                    onClick = {
                        onMovieItemClick(movie)
                    },
                ) {
                    NetworkImage(Constant.POSTER_BASE_URL + imageMovieUrl)
                }
            }
        },
    )
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MoviesPagingGridHorView(
    onMovieItemClick: (MovieModel) -> Unit,
) {

    val movieViewModel = hiltViewModel<HomeViewModel>()

    val getMovie = movieViewModel.getMovieTopRatedPaging()
        .collectAsLazyPagingItems()

    CustomPagingGridHorView(
        lazyPagingItems = getMovie,
        count = 3,
        initItemView = { index ->
            val movie = getMovie[index]
            val imageMovieUrl = movie?.posterPath
            if (movie != null && imageMovieUrl != null) {
                Card(
                    modifier = Modifier
                        .padding(
                            start = dimensions()._7Dp,
                            end = dimensions()._7Dp,
                            bottom = dimensions()._18Dp,
                        )
                        .clip(RoundedCornerShape(dimensions()._16Dp))
                        .height(dimensions()._146Dp)
                        .width(dimensions()._100Dp),
                    backgroundColor = MaterialTheme.colors.surface,
                    elevation = dimensions()._4Dp,
                    onClick = {
                        onMovieItemClick(movie)
                    },
                ) {
                    NetworkImage(Constant.POSTER_BASE_URL + imageMovieUrl)
                }
            }
        },
    )
}