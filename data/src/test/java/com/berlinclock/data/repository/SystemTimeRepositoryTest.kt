package com.berlinclock.data.repository

import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SystemTimeRepositoryTest {

    private lateinit var repository: SystemTimeRepositoryImpl

    @Before
    fun setup() {
        repository = SystemTimeRepositoryImpl()
    }

    @Test
    fun `getCurrentTime returns valid SystemTimeDto`() {
        val result = repository.getCurrentTime()

        assertNotNull(result)
        assert(result.hours in 0..23)
        assert(result.minutes in 0..59)
        assert(result.seconds in 0..59)
    }

    @Test
    fun `getCurrentTime matches system time format`() {
        val beforeCall = Date()
        val result = repository.getCurrentTime()
        val afterCall = Date()

        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val beforeTime = sdf.format(beforeCall)
        val afterTime = sdf.format(afterCall)

        val resultTime = String.format("%02d:%02d:%02d", result.hours, result.minutes, result.seconds)

        assert(resultTime >= beforeTime && resultTime <= afterTime)
    }

    @Test
    fun `getCurrentTime returns different values over time`() {
        val result1 = repository.getCurrentTime()
        Thread.sleep(1100)
        val result2 = repository.getCurrentTime()

        val isDifferent = result1.hours != result2.hours ||
                result1.minutes != result2.minutes ||
                result1.seconds != result2.seconds

        assert(isDifferent)
    }

    @Test
    fun `getCurrentTime components are properly parsed`() {
        val result = repository.getCurrentTime()

        val timeString = String.format("%02d:%02d:%02d", result.hours, result.minutes, result.seconds)
        val parts = timeString.split(":")

        assertEquals(3, parts.size)
        assertEquals(result.hours, parts[0].toInt())
        assertEquals(result.minutes, parts[1].toInt())
        assertEquals(result.seconds, parts[2].toInt())
    }
}