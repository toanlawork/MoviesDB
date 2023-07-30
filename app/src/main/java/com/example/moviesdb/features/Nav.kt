package com.example.moviesdb.features

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesdb.Constant
import com.example.moviesdb.MainScreen
import com.example.moviesdb.R
import com.example.moviesdb.features.detail.MovieDetailScreen
import com.example.moviesdb.features.home.HomeScreen
import com.example.moviesdb.features.search.SearchScreen
import com.example.moviesdb.features.splash.SplashScreen
import com.example.moviesdb.features.watchlist.WatchListScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun Nav() {

    val navController = rememberNavController()

}


@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun navigationSplashScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController)
        }
        composable("main_screen") {
            MainNavHost()
        }
    }
}
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun NavigationHome(
    navController: NavHostController,
    pagerState: PagerState,
) {
    NavHost(navController, startDestination = HomeBottomItem.HOME.route) {
        composable(HomeBottomItem.HOME.route) {
            HomeScreen(pagerState, navController)
        }
        composable(HomeBottomItem.SEARCH.route) {
            SearchScreen(pagerState, navController)
        }
        composable(HomeBottomItem.WATCHLIST.route) {
            WatchListScreen()
        }
    }
}


@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val movieDetailRouter = stringResource(
        id = R.string.movie_detail_router,
        DetailNavItem.DETAIL.route,
        Constant.MOVIE_ID,
    )
    NavHost(navController, startDestination = DetailNavItem.HOME.route) {
        composable(DetailNavItem.HOME.route) {
            MainScreen(mainNavController = navController)
        }

        composable(
            route = movieDetailRouter,
            arguments = listOf(navArgument(Constant.MOVIE_ID) { type = NavType.IntType }),
        ) { backStackEntry ->
            val movieId = remember {
                backStackEntry.arguments?.getInt(Constant.MOVIE_ID)
            }
            movieId?.let {
                MovieDetailScreen(id = it, navController = navController)
            }
        }
    }
}