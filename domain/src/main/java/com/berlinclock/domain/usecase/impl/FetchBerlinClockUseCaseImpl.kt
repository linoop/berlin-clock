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
            topHourRow = getTopHourRow(timeData.hours),
            bottomHourRow = getBottomHourRow(timeData.hours),
        )

    }

    private fun getSecondsLight(seconds: Int) = seconds % 2 == 0

    private fun getTopHourRow(hours: Int): String {
        val lightsOn = hours / 5
        return "R".repeat(lightsOn) + "O".repeat(4 - lightsOn)
    }

    private fun getBottomHourRow(hours: Int): String {
        val lightsOn = hours % 5
        return "R".repeat(lightsOn) + "O".repeat(4 - lightsOn)
    }

}