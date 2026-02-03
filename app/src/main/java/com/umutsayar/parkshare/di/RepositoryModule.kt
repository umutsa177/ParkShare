package com.umutsayar.parkshare.di

import com.umutsayar.parkshare.data.remote.api.auth.AuthService
import com.umutsayar.parkshare.data.remote.api.parking.ParkingService
import com.umutsayar.parkshare.data.remote.api.reservation.ReservationService
import com.umutsayar.parkshare.data.remote.api.review.ReviewService
import com.umutsayar.parkshare.data.repository.*
import com.umutsayar.parkshare.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService
    ): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

    @Provides
    @Singleton
    fun provideParkingSpotRepository(
        parkingService: ParkingService
    ): ParkingSpotRepository {
        return ParkingSpotRepositoryImpl(parkingService)
    }

    @Provides
    @Singleton
    fun provideReservationRepository(
        reservationService: ReservationService
    ): ReservationRepository {
        return ReservationRepositoryImpl(reservationService)
    }

    @Provides
    @Singleton
    fun provideReviewRepository(
        reviewService: ReviewService
    ): ReviewRepository {
        return ReviewRepositoryImpl(reviewService)
    }
}