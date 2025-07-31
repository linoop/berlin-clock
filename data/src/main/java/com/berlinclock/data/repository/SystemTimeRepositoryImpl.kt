package com.berlinclock.data.repository

import com.berlinclock.domain.model.TimeEntity
import com.berlinclock.domain.repository.SystemTimeRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class SystemTimeRepositoryImpl @Inject constructor() : SystemTimeRepository {
    override fun getCurrentTime(): TimeEntity {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val parts = sdf.format(Date()).split(":")
        return TimeEntity(
            hours = parts[0].toInt(),
            minutes = parts[1].toInt(),
            seconds = parts[2].toInt()
        )
    }
}