package com.example.moviesdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moviesdb.features.HomeBottomItem
import com.example.moviesdb.features.MainNavHost
import com.example.moviesdb.features.NavigationHome
import com.example.moviesdb.features.home.HomeScreen
import com.example.moviesdb.features.navigationSplashScreen
import com.example.moviesdb.features.search.SearchScreen
import com.example.moviesdb.features.watchlist.WatchListScreen
import com.example.moviesdb.ui.theme.MoviesDBTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch



@ExperimentalCoroutinesApi
@AndroidEntryPoint
@ExperimentalMaterial3Api
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navigationSplashScreen()
                }
            }
        }
    }
}

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(mainNavController: NavHostController) {
    val navController = rememberNavController()
    val items = listOf(
        HomeBottomItem.HOME,
        HomeBottomItem.SEARCH,
        HomeBottomItem.WATCHLIST,
    )

    val pagerState = rememberPagerState()

    Scaffold(
        topBar = { },
        bottomBar = { bottomNav(navController, pagerState, items) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NavigationHome(navController = navController, pagerState = pagerState)
            }
            HorizontalPager(
                state = pagerState,
                count = 5,
            ) { page ->
                when (page) {
                    0 -> HomeScreen(pagerState, mainNavController)
                    1 -> SearchScreen(pagerState, mainNavController)
                    2 -> WatchListScreen()
                }
            }
        },
        containerColor = colorResource(R.color.back_ground), // Set background color to avoid the white flashing when you switch between screens
    )
}

@ExperimentalPagerApi
@Composable
fun bottomNav(
    navController: NavController,
    pageState: PagerState,
    items: List<HomeBottomItem>,
) {
    val scope = rememberCoroutineScope()
    NavigationBar(
        containerColor = colorResource(id = R.color.back_ground),
        contentColor = colorResource(id = R.color.color_0296E5),
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                    )
                },
                label = { Text(text = stringResource(id = item.title)) },
                alwaysShowLabel = false,
                selected = pageState.currentPage == index,
                selectedContentColor = colorResource(id = R.color.color_0296E5),
                unselectedContentColor = colorResource(id = R.color.color_67686D),
                onClick = {
                    scope.launch {
                        pageState.animateScrollToPage(index)
                    }

                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
    }
}
