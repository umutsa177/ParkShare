package com.umutsayar.parkshare.data.repository

import com.umutsayar.parkshare.data.remote.api.reservation.ReservationService
import com.umutsayar.parkshare.data.remote.dto.reservation.*
import com.umutsayar.parkshare.data.remote.mapper.*
import com.umutsayar.parkshare.domain.model.*
import com.umutsayar.parkshare.domain.repository.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val api: ReservationService
) : ReservationRepository {

    override suspend fun createReservation(
        spotId: String,
        startDate: String,
        endDate: String,
        pricingType: PricingType,
        notes: String?
    ): Flow<Resource<Reservation>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.createReservation(
                CreateReservationRequest(
                    spotId = spotId,
                    startDate = startDate,
                    endDate = endDate,
                    pricingType = pricingType.apiValue,
                    notes = notes
                )
            )

            if (response.isSuccessful && response.body()?.success == true) {
                val reservation = response.body()?.data?.reservation
                if (reservation != null) {
                    emit(Resource.Success(reservation.toDomain()))
                } else {
                    emit(Resource.Error("Rezervasyon oluşturulamadı"))
                }
            } else {
                emit(Resource.Error(response.body()?.message ?: "Rezervasyon başarısız"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun getMyBookings(
        status: ReservationStatus?
    ): Flow<Resource<List<Reservation>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getMyBookings(status?.apiValue)

            if (response.isSuccessful && response.body()?.success == true) {
                val reservations = response.body()?.data?.reservations ?: emptyList()
                emit(Resource.Success(reservations.toDomainList()))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Rezervasyonlar alınamadı"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun getMyListings(
        status: ReservationStatus?
    ): Flow<Resource<List<Reservation>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getMyListings(status?.apiValue)

            if (response.isSuccessful && response.body()?.success == true) {
                val reservations = response.body()?.data?.reservations ?: emptyList()
                emit(Resource.Success(reservations.toDomainList()))
            } else {
                emit(Resource.Error(response.body()?.message ?: "İlanlar alınamadı"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun updateReservationStatus(
        reservationId: String,
        status: ReservationStatus,
        cancellationReason: String?
    ): Flow<Resource<Reservation>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.updateReservationStatus(
                reservationId,
                UpdateReservationStatusRequest(status.apiValue, cancellationReason)
            )

            if (response.isSuccessful && response.body()?.success == true) {
                val reservation = response.body()?.data?.reservation
                if (reservation != null) {
                    emit(Resource.Success(reservation.toDomain()))
                } else {
                    emit(Resource.Error("Güncelleme başarısız"))
                }
            } else {
                emit(Resource.Error(response.body()?.message ?: "Durum güncellenemedi"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }
}