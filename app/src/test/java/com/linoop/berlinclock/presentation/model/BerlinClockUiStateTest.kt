package com.linoop.berlinclock.presentation.model

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class BerlinClockUiStateTest {

    @Test
    fun `constructor creates berlin clock state with default values`() {
        val state = BerlinClockUiState()

        assertFalse(state.secondsLight)
        assertEquals("", state.topHourRow)
        assertEquals("", state.bottomHourRow)
        assertEquals("", state.topMinuteRow)
        assertEquals("", state.bottomMinuteRow)
    }

    @Test
    fun `constructor creates BerlinClockUiState with custom values`() {
        val state = BerlinClockUiState(
            secondsLight = true,
            topHourRow = "RROO",
            bottomHourRow = "RRRO",
            topMinuteRow = "YYRYYRYYRYO",
            bottomMinuteRow = "YYRY"
        )

        assertTrue(state.secondsLight)
        assertEquals("RROO", state.topHourRow)
        assertEquals("RRRO", state.bottomHourRow)
        assertEquals("YYRYYRYYRYO", state.topMinuteRow)
        assertEquals("YYRY", state.bottomMinuteRow)
    }

    @Test
    fun `componentN functions return correct values`() {
        val state = BerlinClockUiState(
            secondsLight = true,
            topHourRow = "RROO",
            bottomHourRow = "RRRO",
            topMinuteRow = "YYRYYRYYRYO",
            bottomMinuteRow = "YYRY"
        )

        assertTrue(state.component1())
        assertEquals("RROO", state.component2())
        assertEquals("RRRO", state.component3())
        assertEquals("YYRYYRYYRYO", state.component4())
        assertEquals("YYRY", state.component5())
    }

    @Test
    fun `destructuring works correctly`() {
        val state = BerlinClockUiState(
            secondsLight = true,
            topHourRow = "RROO",
            bottomHourRow = "RRRO",
            topMinuteRow = "YYRYYRYYRYO",
            bottomMinuteRow = "YYRY"
        )
        val (secondsLight, topHourRow, bottomHourRow, topMinuteRow, bottomMinuteRow) = state

        assertTrue(secondsLight)
        assertEquals("RROO", topHourRow)
        assertEquals("RRRO", bottomHourRow)
        assertEquals("YYRYYRYYRYO", topMinuteRow)
        assertEquals("YYRY", bottomMinuteRow)
    }
}