package com.example.moviesdb.features.customview

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.moviesdb.R
import com.example.moviesdb.ui.theme.dimensions

@Composable
fun HeaderView(
    title: String,
    imageRight: Int? = null,
    onIconEndClick: () -> Unit = { },
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensions()._10Dp, bottom = dimensions()._42Dp),
    ) {
        val (startIcon, text, endIcon) = createRefs()

        Text(
            text = title,
            color = colorResource(id = R.color.white),
            style = TextStyle(
                fontSize = dimensions()._16Sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier.constrainAs(text) {
                start.linkTo(parent.end)
                end.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
        )

    }
}
