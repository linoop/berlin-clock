package com.berlinclock.domain.repository

import com.berlinclock.domain.model.TimeEntity

interface SystemTimeRepository {
    fun getCurrentTime(): TimeEntity
}