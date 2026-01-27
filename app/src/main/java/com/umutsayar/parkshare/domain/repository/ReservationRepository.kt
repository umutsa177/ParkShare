package com.umutsayar.parkshare.domain.repository

import com.umutsayar.parkshare.domain.model.*
import kotlinx.coroutines.flow.Flow

interface ReservationRepository {
    suspend fun createReservation(
        spotId: String,
        startDate: String,
        endDate: String,
        pricingType: PricingType,
        notes: String?
    ): Flow<Resource<Reservation>>

    suspend fun getMyBookings(
        status: ReservationStatus? = null
    ): Flow<Resource<List<Reservation>>>

    suspend fun getMyListings(
        status: ReservationStatus? = null
    ): Flow<Resource<List<Reservation>>>

    suspend fun updateReservationStatus(
        reservationId: String,
        status: ReservationStatus,
        cancellationReason: String? = null
    ): Flow<Resource<Reservation>>
}