package com.berlinclock.domain.usecase

import com.berlinclock.domain.model.BerlinClockData

interface FetchBerlinClockUseCase {
    fun getBerlinClock(): BerlinClockData
}