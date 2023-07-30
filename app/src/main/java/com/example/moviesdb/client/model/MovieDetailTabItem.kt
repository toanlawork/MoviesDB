package com.example.moviesdb.client.model

import androidx.compose.runtime.Composable

data class MovieDetailTabItem(
    val text: String, // Tab Title
    val screen: @Composable () -> Unit,
)
