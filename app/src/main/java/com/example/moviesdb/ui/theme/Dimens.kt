package com.example.moviesdb.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LocalDim = compositionLocalOf { Dimensions() }

data class Dimensions(
    val _0Dp: Dp = 0.dp,
    val _1Dp: Dp = 1.dp,
    val _2Dp: Dp = 2.dp,
    val _4Dp: Dp = 4.dp,
    val _7Dp: Dp = 7.dp,
    val _8Dp: Dp = 8.dp,
    val _10Dp: Dp = 10.dp,
    val _12Dp: Dp = 12.dp,
    val _16Dp: Dp = 16.dp,
    val _18Dp: Dp = 18.dp,
    val _20Dp: Dp = 20.dp,
    val _21Dp: Dp = 21.dp,
    val _24Dp: Dp = 24.dp,
    val _30Dp: Dp = 30.dp,
    val _42Dp: Dp = 42.dp,
    val _50Dp: Dp = 50.dp,
    val _95Dp: Dp = 95.dp,
    val _100Dp: Dp = 100.dp,
    val _120Dp: Dp = 120.dp,
    val _144Dp: Dp = 144.dp,
    val _146Dp: Dp = 146.dp,
    val _210Dp: Dp = 210.dp,
    val _14Sp: TextUnit = 14.sp,
    val _16Sp: TextUnit = 16.sp,
    val _18Sp: TextUnit = 18.sp,
)

@Composable
fun dimensions() = LocalDim.current
