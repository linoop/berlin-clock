package com.linoop.berlinclock.presentation.ui.util

import androidx.compose.ui.graphics.Color

fun mapCharToColor(c: Char): Color = when (c) {
    'R' -> Color.Red
    'Y' -> Color.Yellow
    else -> Color.Gray
}