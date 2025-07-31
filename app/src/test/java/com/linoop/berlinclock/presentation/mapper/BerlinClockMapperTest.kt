package com.linoop.berlinclock.presentation.mapper

import com.berlinclock.domain.model.BerlinClockData
import com.linoop.berlinclock.presentation.model.BerlinClockUiState
import org.junit.Test
import kotlin.test.assertEquals

class BerlinClockMapperTest {

    @Test
    fun `toBerlinClockUiState maps all fields correctly`() {
        val data = BerlinClockData(
            secondsLight = true,
            topHourRow = "RRRO",
            bottomHourRow = "RROO",
            topMinuteRow = "YYRYYRYYRYY",
            bottomMinuteRow = "YYOO"
        )

        val expected = BerlinClockUiState(
            secondsLight = true,
            topHourRow = "RRRO",
            bottomHourRow = "RROO",
            topMinuteRow = "YYRYYRYYRYY",
            bottomMinuteRow = "YYOO"
        )

        val result = data.toBerlinClockUiState()

        assertEquals(expected, result)
    }
}