package com.berlinclock.domain.usecase.impl

import com.berlinclock.domain.repository.SystemTimeRepository
import com.berlinclock.domain.usecase.FetchDigitalClockUseCase
import javax.inject.Inject

class FetchDigitalClockUseCaseImpl @Inject constructor(
    private val systemTimeRepository: SystemTimeRepository
): FetchDigitalClockUseCase {
    override fun getNormalClockTime(): String {
        val currentTime = systemTimeRepository.getCurrentTime()
        return "${currentTime.hours}:${currentTime.minutes}:${currentTime.seconds}"
    }
}