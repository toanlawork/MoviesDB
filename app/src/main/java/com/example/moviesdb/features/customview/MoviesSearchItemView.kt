package com.example.moviesdb.features.customview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.moviesdb.Constant
import com.example.moviesdb.R
import com.example.moviesdb.client.model.Genre
import com.example.moviesdb.client.model.MovieModel
import com.example.moviesdb.ui.theme.dimensions

@ExperimentalMaterialApi
@Composable
fun MovieSearchItemView(
    movie: MovieModel,
    onMovieItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(
                start = dimensions()._16Dp,
                end = dimensions()._16Dp,
                bottom = dimensions()._24Dp,
            )
            .clip(RoundedCornerShape(dimensions()._16Dp)),
        elevation = dimensions()._4Dp,
        backgroundColor = colorResource(id = R.color.back_ground),
        onClick = {
            onMovieItemClick()
        },
    ) {
        Row {
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensions()._16Dp)),
                elevation = dimensions()._4Dp,
            ) {
                NetworkImage(
                    url = Constant.POSTER_BASE_URL + movie.posterPath,
                )
            }

            Column(
                modifier = Modifier.padding(
                    start = dimensions()._12Dp,
                ),
            ) {
                Text(
                    text = movie.title ?: "",
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.white),
                    textAlign = TextAlign.Center,
                    fontSize = dimensions()._16Sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                TextWithIcon(
                    icon = ImageVector.vectorResource(R.drawable.ic_vote_star),
                    text = movie.voteAverage ?: "",
                    modifier = Modifier.padding(top = dimensions()._10Dp),
                    iconTintColor = colorResource(id = R.color.color_FF8700),
                )

                TextWithIcon(
                    icon = ImageVector.vectorResource(R.drawable.ic_genre),
                    text = movie.genres.convertGenres(),
                    modifier = Modifier.padding(top = dimensions()._4Dp),
                )

                TextWithIcon(
                    icon = ImageVector.vectorResource(R.drawable.ic_calendar),
                    text = movie.releaseDate ?: "",
                    modifier = Modifier.padding(top = dimensions()._4Dp),
                )

                TextWithIcon(
                    icon = ImageVector.vectorResource(R.drawable.ic_clock),
                    text = stringResource(id = R.string.run_time, movie.runtime ?: 0),
                    modifier = Modifier.padding(top = dimensions()._4Dp),
                )
            }
        }
    }
}

fun List<Genre>?.convertGenres(): String {
    return if (!this.isNullOrEmpty()) {
        this.joinToString(
            separator = " | ",
            transform = { genre -> genre.name.toString() },
        )
    } else {
        ""
    }
}
