package com.linoop.berlinclock.presentation.model

data class BerlinClockUiState(
    val secondsLight: Boolean = false,
    val topHourRow: String = "",
    val bottomHourRow: String = "",
    val topMinuteRow: String = "",
    val bottomMinuteRow: String = ""
)
