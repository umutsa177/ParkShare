package com.umutsayar.parkshare.domain.repository

import com.umutsayar.parkshare.domain.model.*
import kotlinx.coroutines.flow.Flow

interface ParkingSpotRepository {
    suspend fun getNearbyParkingSpots(
        latitude: Double? = null,
        longitude: Double? = null,
        radius: Int? = 5000,
        features: List<String>? = null,
        minPrice: Double? = null,
        maxPrice: Double? = null
    ): Flow<Resource<List<ParkingSpot>>>

    suspend fun getParkingSpotById(
        spotId: String
    ): Flow<Resource<ParkingSpot>>

    suspend fun createParkingSpot(
        spot: ParkingSpot
    ): Flow<Resource<ParkingSpot>>

    suspend fun getMyParkingSpots(): Flow<Resource<List<ParkingSpot>>>

    suspend fun deleteParkingSpot(
        spotId: String
    ): Flow<Resource<Unit>>
}

// =====