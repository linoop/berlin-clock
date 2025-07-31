package com.linoop.berlinclock.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.linoop.berlinclock.presentation.model.BerlinClockUiState
import com.linoop.berlinclock.presentation.ui.util.mapCharToColor

@Composable
fun BerlinClockDisplay(
    berlinClockUiState: BerlinClockUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SecondsLight(isOn = berlinClockUiState.secondsLight)
        HourRow(lights = berlinClockUiState.topHourRow)
        HourRow(lights = berlinClockUiState.bottomHourRow)
        MinuteRow(lights = berlinClockUiState.topMinuteRow, isTopRow = true)
        MinuteRow(lights = berlinClockUiState.bottomMinuteRow, isTopRow = false)
    }
}

@Composable
private fun SecondsLight(isOn: Boolean) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(if (isOn) Color.Yellow else Color.Gray)
            .border(2.dp, Color.Black, CircleShape)
    )
}

@Composable
private fun HourRow(lights: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        lights.forEach { light ->
            LightBlock(
                color = mapCharToColor(light),
                size = 60.dp
            )
        }
    }
}

@Composable
private fun MinuteRow(lights: String, isTopRow: Boolean) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        lights.forEach { light ->
            LightBlock(
                color = mapCharToColor(light),
                size = if (isTopRow) 25.dp else 40.dp
            )
        }
    }
}

@Composable
private fun LightBlock(
    color: Color,
    size: Dp
) {
    Box(
        modifier = Modifier
            .width(size)
            .height(40.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(color)
            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
    )
}