package com.linoop.berlinclock.di

import com.berlinclock.data.repository.SystemTimeRepositoryImpl
import com.berlinclock.domain.repository.SystemTimeRepository
import com.berlinclock.domain.usecase.FetchBerlinClockUseCase
import com.berlinclock.domain.usecase.FetchDigitalClockUseCase
import com.berlinclock.domain.usecase.impl.FetchBerlinClockUseCaseImpl
import com.berlinclock.domain.usecase.impl.FetchDigitalClockUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ClockModule {

    @Binds
    @Singleton
    abstract fun bindBerlinClockUseCase(
        berlinClockUseCaseImpl: FetchBerlinClockUseCaseImpl
    ): FetchBerlinClockUseCase

    @Binds
    @Singleton
    abstract fun provideTimeRepository(
        timeRepository: SystemTimeRepositoryImpl
    ): SystemTimeRepository

    @Binds
    @Singleton
    abstract fun bindNormalClockUseCase(
        normalClockUseCase: FetchDigitalClockUseCaseImpl
    ): FetchDigitalClockUseCase
}