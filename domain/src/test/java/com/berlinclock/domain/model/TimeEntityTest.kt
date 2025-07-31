package com.berlinclock.domain.model

import org.junit.Test
import kotlin.test.assertEquals

class TimeEntityTest {
    @Test
    fun `constructor creates TimeEntity with valid values`() {
        val dto = TimeEntity(hours = 12, minutes = 30, seconds = 45)

        assertEquals(12, dto.hours)
        assertEquals(30, dto.minutes)
        assertEquals(45, dto.seconds)
    }

    @Test
    fun `componentN functions return correct values`() {
        val dto = TimeEntity(hours = 12, minutes = 30, seconds = 45)

        assertEquals(12, dto.component1())
        assertEquals(30, dto.component2())
        assertEquals(45, dto.component3())
    }

    @Test
    fun `destructuring works correctly`() {
        val dto = TimeEntity(hours = 12, minutes = 30, seconds = 45)
        val (hours, minutes, seconds) = dto

        assertEquals(12, hours)
        assertEquals(30, minutes)
        assertEquals(45, seconds)
    }
}