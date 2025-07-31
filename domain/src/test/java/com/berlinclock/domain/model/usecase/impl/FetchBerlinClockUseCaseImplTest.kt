package com.berlinclock.domain.model.usecase.impl

import com.berlinclock.domain.model.BerlinClockData
import com.berlinclock.domain.model.TimeEntity
import com.berlinclock.domain.repository.SystemTimeRepository
import com.berlinclock.domain.usecase.impl.FetchBerlinClockUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FetchBerlinClockUseCaseImplTest {

    private lateinit var currentTimeProvider: SystemTimeRepository
    private lateinit var useCase: FetchBerlinClockUseCaseImpl

    @Before
    fun setup() {
        currentTimeProvider = mockk()
        useCase = FetchBerlinClockUseCaseImpl(currentTimeProvider)
    }

    @Test
    fun `getBerlinClock handles seconds light correctly for even seconds`() {
        val timeDto = TimeEntity(hours = 10, minutes = 30, seconds = 42)
        every { currentTimeProvider.getCurrentTime() } returns timeDto

        val result = useCase.getBerlinClock()

        assertTrue(result.secondsLight)
        verify { currentTimeProvider.getCurrentTime() }
    }

    @Test
    fun `getBerlinClock handles seconds light correctly for odd seconds`() {
        val timeDto = TimeEntity(hours = 10, minutes = 30, seconds = 43)
        every { currentTimeProvider.getCurrentTime() } returns timeDto

        val result = useCase.getBerlinClock()

        assertFalse(result.secondsLight)
        verify { currentTimeProvider.getCurrentTime() }
    }

    @Test
    fun `getBerlinClock handles top hour row for various hours`() {
        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(1, 0, 0)
        assertEquals("OOOO", useCase.getBerlinClock().topHourRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(5, 0, 0)
        assertEquals("ROOO", useCase.getBerlinClock().topHourRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(10, 0, 0)
        assertEquals("RROO", useCase.getBerlinClock().topHourRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(15, 0, 0)
        assertEquals("RRRO", useCase.getBerlinClock().topHourRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(20, 0, 0)
        assertEquals("RRRR", useCase.getBerlinClock().topHourRow)
    }

    @Test
    fun `getBerlinClock handles bottom hour row for various hours`() {
        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(1, 0, 0)
        assertEquals("ROOO", useCase.getBerlinClock().bottomHourRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(7, 0, 0)
        assertEquals("RROO", useCase.getBerlinClock().bottomHourRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(13, 0, 0)
        assertEquals("RRRO", useCase.getBerlinClock().bottomHourRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(19, 0, 0)
        assertEquals("RRRR", useCase.getBerlinClock().bottomHourRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(20, 0, 0)
        assertEquals("OOOO", useCase.getBerlinClock().bottomHourRow)
    }

    @Test
    fun `getBerlinClock handles top minute row for quarter positions`() {
        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(0, 15, 0)
        assertEquals("YYROOOOOOOO", useCase.getBerlinClock().topMinuteRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(0, 30, 0)
        assertEquals("YYRYYROOOOO", useCase.getBerlinClock().topMinuteRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(0, 45, 0)
        assertEquals("YYRYYRYYROO", useCase.getBerlinClock().topMinuteRow)
    }

    @Test
    fun `getBerlinClock handles top minute row for various minutes`() {
        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(0, 5, 0)
        assertEquals("YOOOOOOOOOO", useCase.getBerlinClock().topMinuteRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(0, 25, 0)
        assertEquals("YYRYYOOOOOO", useCase.getBerlinClock().topMinuteRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(0, 55, 0)
        assertEquals("YYRYYRYYRYY", useCase.getBerlinClock().topMinuteRow)
    }


    @Test
    fun `getBerlinClock handles bottom minute row for various minutes`() {
        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(0, 1, 0)
        assertEquals("YOOO", useCase.getBerlinClock().bottomMinuteRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(0, 7, 0)
        assertEquals("YYOO", useCase.getBerlinClock().bottomMinuteRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(0, 13, 0)
        assertEquals("YYYO", useCase.getBerlinClock().bottomMinuteRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(0, 19, 0)
        assertEquals("YYYY", useCase.getBerlinClock().bottomMinuteRow)

        every { currentTimeProvider.getCurrentTime() } returns TimeEntity(0, 20, 0)
        assertEquals("OOOO", useCase.getBerlinClock().bottomMinuteRow)
    }

    @Test
    fun `getBerlinClock handles specific time 14-35-27`() {
        val timeDto = TimeEntity(hours = 14, minutes = 35, seconds = 27)
        every { currentTimeProvider.getCurrentTime() } returns timeDto

        val result = useCase.getBerlinClock()

        assertEquals(
            BerlinClockData(
                secondsLight = false,
                topHourRow = "RROO",
                bottomHourRow = "RRRR",
                topMinuteRow = "YYRYYRYOOOO",
                bottomMinuteRow = "OOOO"
            ),
            result
        )
        verify { currentTimeProvider.getCurrentTime() }
    }


}