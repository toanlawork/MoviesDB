package com.example.moviesdb.features

import com.example.moviesdb.Constant
import com.example.moviesdb.R

enum class HomeBottomItem(
    val index: Int,
    var route: String,
    var icon: Int,
    var title: Int
) {
    HOME(index = 0, Constant.HOME, R.drawable.ic_home, R.string.home),
    SEARCH(index = 1, Constant.SEARCH, R.drawable.ic_search, R.string.search),
    WATCHLIST(index = 2, Constant.WATCH_LIST, R.drawable.ic_watch, R.string.watch_list),
}


enum class DetailNavItem(var route: String, var title: Int) {
    HOME(Constant.HOME, R.string.home),
    DETAIL(Constant.MOVIE_DETAIL, R.string.watch_list)
}