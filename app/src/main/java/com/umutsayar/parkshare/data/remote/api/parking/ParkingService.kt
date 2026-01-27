package com.umutsayar.parkshare.data.remote.api.parking

import com.umutsayar.parkshare.data.remote.dto.*
import com.umutsayar.parkshare.data.remote.dto.parking.*
import retrofit2.Response
import retrofit2.http.*

interface ParkingService {
    @GET("parking-spots")
    suspend fun getNearbyParkingSpots(
        @Query("lat") latitude: Double? = null,
        @Query("lng") longitude: Double? = null,
        @Query("radius") radius: Int? = 5000,
        @Query("features") features: String? = null,
        @Query("minPrice") minPrice: Double? = null,
        @Query("maxPrice") maxPrice: Double? = null
    ): Response<ApiResponse<ParkingSpotsResponseDto>>

    @GET("parking-spots/{id}")
    suspend fun getParkingSpotById(
        @Path("id") spotId: String
    ): Response<ApiResponse<ParkingSpotResponseDto>>

    @POST("parking-spots")
    suspend fun createParkingSpot(
        @Body request: CreateParkingSpotRequest
    ): Response<ApiResponse<ParkingSpotResponseDto>>

    @PUT("parking-spots/{id}")
    suspend fun updateParkingSpot(
        @Path("id") spotId: String,
        @Body request: CreateParkingSpotRequest
    ): Response<ApiResponse<ParkingSpotResponseDto>>

    @DELETE("parking-spots/{id}")
    suspend fun deleteParkingSpot(
        @Path("id") spotId: String
    ): Response<ApiResponse<Unit>>

    @GET("parking-spots/owner/my-spots")
    suspend fun getMyParkingSpots(): Response<ApiResponse<ParkingSpotsResponseDto>>

    @PATCH("parking-spots/{id}/availability")
    suspend fun updateAvailability(
        @Path("id") spotId: String,
        @Body availability: Map<String, Boolean>
    ): Response<ApiResponse<Unit>>
}