package com.example.moviesdb.features.customview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.moviesdb.R
import com.example.moviesdb.ui.theme.dimensions

@Composable
fun MoviesSearchView(
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
            if (onSearchMoviesClick != null && text.length > 1) {
                onSearchMoviesClick(text)
            }
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
