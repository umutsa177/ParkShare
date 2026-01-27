package com.umutsayar.parkshare.data.repository

import com.umutsayar.parkshare.data.remote.api.parking.ParkingService
import com.umutsayar.parkshare.data.remote.mapper.*
import com.umutsayar.parkshare.domain.model.*
import com.umutsayar.parkshare.domain.repository.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ParkingSpotRepositoryImpl @Inject constructor(
    private val api: ParkingService
) : ParkingSpotRepository {

    override suspend fun getNearbyParkingSpots(
        latitude: Double?,
        longitude: Double?,
        radius: Int?,
        features: List<String>?,
        minPrice: Double?,
        maxPrice: Double?
    ): Flow<Resource<List<ParkingSpot>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getNearbyParkingSpots(
                latitude = latitude,
                longitude = longitude,
                radius = radius,
                features = features?.joinToString(","),
                minPrice = minPrice,
                maxPrice = maxPrice
            )

            if (response.isSuccessful && response.body()?.success == true) {
                val spots = response.body()?.data?.spots ?: emptyList()
                emit(Resource.Success(spots.toDomainList()))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Park yerleri alınamadı"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun getParkingSpotById(
        spotId: String
    ): Flow<Resource<ParkingSpot>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getParkingSpotById(spotId)

            if (response.isSuccessful && response.body()?.success == true) {
                val spot = response.body()?.data?.spot
                if (spot != null) {
                    emit(Resource.Success(spot.toDomain()))
                } else {
                    emit(Resource.Error("Park yeri bulunamadı"))
                }
            } else {
                emit(Resource.Error(response.body()?.message ?: "Detay alınamadı"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun createParkingSpot(
        spot: ParkingSpot
    ): Flow<Resource<ParkingSpot>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.createParkingSpot(spot.toCreateRequest())

            if (response.isSuccessful && response.body()?.success == true) {
                val createdSpot = response.body()?.data?.spot
                if (createdSpot != null) {
                    emit(Resource.Success(createdSpot.toDomain()))
                } else {
                    emit(Resource.Error("İlan oluşturulamadı"))
                }
            } else {
                emit(Resource.Error(response.body()?.message ?: "İlan eklenemedi"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun getMyParkingSpots(): Flow<Resource<List<ParkingSpot>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getMyParkingSpots()

            if (response.isSuccessful && response.body()?.success == true) {
                val spots = response.body()?.data?.spots ?: emptyList()
                emit(Resource.Success(spots.toDomainList()))
            } else {
                emit(Resource.Error(response.body()?.message ?: "İlanlar alınamadı"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun deleteParkingSpot(spotId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.deleteParkingSpot(spotId)

            if (response.isSuccessful && response.body()?.success == true) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Silinemedi"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }
}
