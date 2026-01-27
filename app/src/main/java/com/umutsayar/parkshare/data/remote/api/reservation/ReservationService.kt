package com.umutsayar.parkshare.data.remote.api.reservation

import com.umutsayar.parkshare.data.remote.dto.*
import com.umutsayar.parkshare.data.remote.dto.reservation.*
import retrofit2.Response
import retrofit2.http.*

interface ReservationService {
    @POST("reservations")
    suspend fun createReservation(
        @Body request: CreateReservationRequest
    ): Response<ApiResponse<ReservationResponseDto>>

    @GET("reservations/my-bookings")
    suspend fun getMyBookings(
        @Query("status") status: String? = null
    ): Response<ApiResponse<ReservationsResponseDto>>

    @GET("reservations/my-listings")
    suspend fun getMyListings(
        @Query("status") status: String? = null
    ): Response<ApiResponse<ReservationsResponseDto>>

    @GET("reservations/{id}")
    suspend fun getReservationById(
        @Path("id") reservationId: String
    ): Response<ApiResponse<ReservationResponseDto>>

    @PUT("reservations/{id}/status")
    suspend fun updateReservationStatus(
        @Path("id") reservationId: String,
        @Body request: UpdateReservationStatusRequest
    ): Response<ApiResponse<ReservationResponseDto>>
}