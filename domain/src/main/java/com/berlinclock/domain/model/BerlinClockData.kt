package com.berlinclock.domain.model

data class BerlinClockData(
    val secondsLight: Boolean = false,
    val topHourRow: String = "",
    val bottomHourRow: String = "",
    val topMinuteRow: String = "",
    val bottomMinuteRow: String = ""
)
