package com.linoop.berlinclock.presentation.viewmodel

import app.cash.turbine.test
import com.berlinclock.domain.model.BerlinClockData
import com.berlinclock.domain.usecase.FetchBerlinClockUseCase
import com.berlinclock.domain.usecase.FetchDigitalClockUseCase
import com.linoop.berlinclock.presentation.model.BerlinClockUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ClockDisplayViewModelTest {

    private lateinit var viewModel: ClockDisplayViewModel

    private val fetchBerlinClockUseCase = mockk<FetchBerlinClockUseCase>()
    private val fetchDigitalClockUseCase = mockk<FetchDigitalClockUseCase>()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emit digital time when use case returns time`() = runTest {
        coEvery { fetchDigitalClockUseCase.getNormalClockTime() } returns "12:34:56"
        coEvery { fetchBerlinClockUseCase.getBerlinClock() } returns BerlinClockData()

        viewModel = ClockDisplayViewModel(fetchBerlinClockUseCase, fetchDigitalClockUseCase)
        viewModel.updateClock()
        advanceTimeBy(1000L)

        viewModel.currentTime.test {
            assertEquals("12:34:56", awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should emit BerlinClockUiState when use case returns data`() = runTest {
        val berlinClockData = BerlinClockData(
            secondsLight = false,
            topHourRow = "RRRR",
            bottomHourRow = "RRRR",
            topMinuteRow = "YYRYYRYYRYY",
            bottomMinuteRow = "YYYY"
        )
        val expectedState = BerlinClockUiState(
            secondsLight = false,
            topHourRow = "RRRR",
            bottomHourRow = "RRRR",
            topMinuteRow = "YYRYYRYYRYY",
            bottomMinuteRow = "YYYY"
        )
        coEvery { fetchDigitalClockUseCase.getNormalClockTime() } returns "00:00:00"
        coEvery { fetchBerlinClockUseCase.getBerlinClock() } returns berlinClockData

        viewModel = ClockDisplayViewModel(fetchBerlinClockUseCase, fetchDigitalClockUseCase)
        viewModel.updateClock()
        advanceTimeBy(1000L)

        viewModel.berlinClockDisplay.test {
            assertEquals(expectedState, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should emit Error if digital clock use case throws exception`() = runTest {
        coEvery { fetchDigitalClockUseCase.getNormalClockTime() } throws RuntimeException("Fail")
        coEvery { fetchBerlinClockUseCase.getBerlinClock() } returns BerlinClockData()

        viewModel = ClockDisplayViewModel(fetchBerlinClockUseCase, fetchDigitalClockUseCase)
        viewModel.updateClock()
        advanceTimeBy(1000L)

        viewModel.currentTime.test {
            assertEquals("Error", awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should not crash if berlin clock use case throws exception`() = runTest {
        coEvery { fetchBerlinClockUseCase.getBerlinClock() } throws RuntimeException("Fail")
        viewModel = ClockDisplayViewModel(fetchBerlinClockUseCase, fetchDigitalClockUseCase)
        viewModel.updateClock()
        advanceTimeBy(1000L)

        viewModel.berlinClockDisplay.test {
            assertEquals(BerlinClockUiState(), awaitItem()) // initial value
            cancelAndIgnoreRemainingEvents()
        }
    }
}