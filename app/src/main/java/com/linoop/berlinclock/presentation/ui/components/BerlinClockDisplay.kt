package com.linoop.berlinclock.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.linoop.berlinclock.presentation.model.BerlinClockUiState

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