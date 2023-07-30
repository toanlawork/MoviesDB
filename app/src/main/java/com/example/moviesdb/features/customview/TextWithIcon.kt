package com.example.moviesdb.features.customview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import com.example.moviesdb.R
import com.example.moviesdb.ui.theme.dimensions

@Composable
fun TextWithIcon(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    iconTintColor: Color = colorResource(id = R.color.white),
    textColor: Color = colorResource(id = R.color.white),
) {
    Row(modifier = modifier) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = iconTintColor,
            modifier = Modifier.padding(top = dimensions()._2Dp),
        )

        Text(
            text = text,
            color = textColor,
            modifier = Modifier.padding(start = dimensions()._4Dp, end = dimensions()._4Dp),
        )
    }
}
