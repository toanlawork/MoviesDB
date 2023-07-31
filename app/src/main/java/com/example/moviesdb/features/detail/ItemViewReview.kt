package com.example.moviesdb.features.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.moviesdb.R
import com.example.moviesdb.client.model.Review
import com.example.moviesdb.ui.theme.dimensions


@ExperimentalMaterialApi
@Composable
fun ItemViewReview(data: Review) {
    Row(
        modifier = Modifier
            .padding(bottom = dimensions()._12Dp)
            .background(color = colorResource(id = R.color.back_ground))
            .fillMaxWidth(),
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_default_av),
            contentDescription = "",
            modifier = Modifier
                .width(dimensions()._48Dp)
                .height(dimensions()._48Dp),
        )
        Column {
            Text(
                text = data.author ?: "",
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold,
                fontSize = dimensions()._14Sp,
                modifier = Modifier.padding(
                    start = dimensions()._8Dp,
                    bottom = dimensions()._12Dp,
                ),
            )
            Text(
                text = data.content ?: "",
                color = colorResource(id = R.color.white),
                fontSize = dimensions()._12Sp,
                modifier = Modifier.padding(start = dimensions()._8Dp),
            )
        }
    }
}