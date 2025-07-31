package com.berlinclock.domain.model.usecase.impl

import com.berlinclock.domain.model.TimeEntity
import com.berlinclock.domain.repository.SystemTimeRepository
import com.berlinclock.domain.usecase.impl.FetchBerlinClockUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
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
}