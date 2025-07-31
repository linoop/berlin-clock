package com.linoop.berlinclock.presentation.mapper

import com.berlinclock.domain.model.BerlinClockData
import com.linoop.berlinclock.presentation.model.BerlinClockUiState

fun BerlinClockData.toBerlinClockUiState(): BerlinClockUiState {
    return BerlinClockUiState(
        secondsLight = secondsLight,
        topHourRow = topHourRow,
        bottomHourRow = bottomHourRow,
        topMinuteRow = topMinuteRow,
        bottomMinuteRow = bottomMinuteRow
    )
}