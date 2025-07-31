package com.berlinclock.domain.usecase.impl

import com.berlinclock.domain.model.BerlinClockData
import com.berlinclock.domain.repository.SystemTimeRepository
import com.berlinclock.domain.usecase.FetchBerlinClockUseCase
import javax.inject.Inject

class FetchBerlinClockUseCaseImpl @Inject constructor(
    private val systemTimeRepository: SystemTimeRepository
) : FetchBerlinClockUseCase {


    override fun getBerlinClock(): BerlinClockData {
        val timeData = systemTimeRepository.getCurrentTime()

        return BerlinClockData(
            secondsLight = getSecondsLight(timeData.seconds),
        )

    }

    private fun getSecondsLight(seconds: Int) = seconds % 2 == 0

}