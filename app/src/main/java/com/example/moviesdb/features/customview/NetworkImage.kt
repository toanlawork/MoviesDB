package com.example.moviesdb.features.customview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesdb.R

@Composable
fun NetworkImage(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
        error = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = stringResource(R.string.description),
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}
