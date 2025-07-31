package com.berlinclock.domain.model.usecase.impl

import com.berlinclock.domain.model.TimeEntity
import com.berlinclock.domain.repository.SystemTimeRepository
import com.berlinclock.domain.usecase.impl.FetchDigitalClockUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.text.contains
import kotlin.text.split

class FetchDigitalClockUseCaseImplTest {

    private lateinit var currentTimeProvider: SystemTimeRepository
    private lateinit var useCase: FetchDigitalClockUseCaseImpl

    @Before
    fun setup() {
        currentTimeProvider = mockk()
        useCase = FetchDigitalClockUseCaseImpl(currentTimeProvider)
    }

    @Test
    fun `getNormalClockTime returns formatted time string`() {
        val timeDto = TimeEntity(hours = 14, minutes = 30, seconds = 45)
        every { currentTimeProvider.getCurrentTime() } returns timeDto

        val result = useCase.getNormalClockTime()

        assertEquals("14:30:45", result)
        verify { currentTimeProvider.getCurrentTime() }
    }

    @Test
    fun `getNormalClockTime calls currentTimeProvider exactly once`() {
        val timeDto = TimeEntity(hours = 10, minutes = 20, seconds = 30)
        every { currentTimeProvider.getCurrentTime() } returns timeDto

        useCase.getNormalClockTime()

        verify(exactly = 1) { currentTimeProvider.getCurrentTime() }
    }

    @Test
    fun `getNormalClockTime returns different results for different times`() {
        val timeDto1 = TimeEntity(hours = 10, minutes = 30, seconds = 45)
        val timeDto2 = TimeEntity(hours = 11, minutes = 30, seconds = 45)

        every { currentTimeProvider.getCurrentTime() } returns timeDto1
        val result1 = useCase.getNormalClockTime()

        every { currentTimeProvider.getCurrentTime() } returns timeDto2
        val result2 = useCase.getNormalClockTime()

        assertEquals("10:30:45", result1)
        assertEquals("11:30:45", result2)
        verify(exactly = 2) { currentTimeProvider.getCurrentTime() }
    }

    @Test
    fun `getNormalClockTime formats time consistently`() {
        val timeDto = TimeEntity(hours = 8, minutes = 9, seconds = 10)
        every { currentTimeProvider.getCurrentTime() } returns timeDto

        val result = useCase.getNormalClockTime()

        assertEquals("8:9:10", result)
        assert(result.contains(":"))
        assert(result.split(":").size == 3)
        verify { currentTimeProvider.getCurrentTime() }
    }
}