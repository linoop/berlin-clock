package com.linoop.berlinclock.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.berlinclock.domain.usecase.FetchBerlinClockUseCase
import com.berlinclock.domain.usecase.FetchDigitalClockUseCase
import com.linoop.berlinclock.presentation.mapper.toBerlinClockUiState
import com.linoop.berlinclock.presentation.model.BerlinClockUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ClockDisplayViewModel @Inject constructor(
    private val fetchBerlinClockUseCase: FetchBerlinClockUseCase,
    private val fetchDigitalClockUseCase: FetchDigitalClockUseCase
) : ViewModel() {

    private val _berlinClockDisplay = MutableStateFlow(BerlinClockUiState())
    val berlinClockDisplay: StateFlow<BerlinClockUiState> = _berlinClockDisplay.asStateFlow()

    private val _currentTime = MutableStateFlow("")
    val currentTime: StateFlow<String> = _currentTime.asStateFlow()

    fun updateClock() = try {
        _currentTime.value = fetchDigitalClockUseCase.getNormalClockTime()
        _berlinClockDisplay.value = fetchBerlinClockUseCase
            .getBerlinClock()
            .toBerlinClockUiState()
    } catch (e: Exception) {
        _currentTime.value = "Error"
    }
}
