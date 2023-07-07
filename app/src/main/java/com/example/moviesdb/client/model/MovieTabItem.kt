package com.example.moviesdb.client.model

import androidx.compose.runtime.Composable

data class MovieTabItem(
    val text: String,
    val icon: Int? = null,
    val screen: @Composable () -> Unit,
)

enum class MovieType {
    POPULAR,
    UP_COMING,
    NOW_PLAYING,
    TOP_RATED
}

