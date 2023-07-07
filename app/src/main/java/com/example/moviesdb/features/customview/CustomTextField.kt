package com.example.moviesdb.features.customview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.moviesdb.R

@Composable
fun CustomTextField(
    modifierBox: Modifier = Modifier,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    leadingPadding: Dp = 0.dp,
    trailingIcon: (@Composable () -> Unit)? = null,
    trailingPadding: Dp = 8.dp,
    text: String,
    onValueChange: (String) -> Unit,
    placeholderText: String = stringResource(id = R.string.search),
    placeholderColor: Color = colorResource(id = R.color.color_67686D),
    cursorColor: Color = colorResource(id = R.color.white),
    textColor: Color = colorResource(id = R.color.white),
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize,
) {
    Box(
        modifier = modifierBox,
    ) {
        BasicTextField(
            modifier = modifier,
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            cursorBrush = SolidColor(cursorColor),
            textStyle = LocalTextStyle.current.copy(
                color = textColor,
                fontSize = fontSize,
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(
                        modifier = Modifier.padding(
                            start = leadingPadding,
                            end = leadingPadding,
                        ),
                    )
                    if (leadingIcon != null) leadingIcon()
                    Box(Modifier.weight(1F)) {
                        if (text.isEmpty()) {
                            Text(
                                placeholderText,
                                style = LocalTextStyle.current.copy(
                                    color = placeholderColor,
                                    fontSize = fontSize,
                                ),
                            )
                        }
                        innerTextField()
                    }
                    Spacer(
                        modifier = Modifier.padding(
                            start = trailingPadding,
                            end = trailingPadding,
                        ),
                    )
                    if (trailingIcon != null) trailingIcon()
                }
            },
        )
    }
}
