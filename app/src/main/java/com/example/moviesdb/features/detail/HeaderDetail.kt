package com.example.moviesdb.features.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.moviesdb.R
import com.example.moviesdb.ui.theme.dimensions


@Composable
fun headerDetail(navController: NavController, modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dimensions()._20Dp, bottom = dimensions()._10Dp),
    ) {
        val (startIcon, text, endIcon) = createRefs()

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.constrainAs(startIcon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            },
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
                contentDescription = "",
                tint = colorResource(id = R.color.white),
            )
        }

        Text(
            text = stringResource(id = R.string.movie_detail),
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

        IconButton(
            onClick = { },
            modifier = Modifier.constrainAs(endIcon) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_watch),
                contentDescription = "",
                tint = colorResource(id = R.color.white),
            )
        }

    }
}