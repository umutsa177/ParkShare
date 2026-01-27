package com.umutsayar.parkshare.data.remote.dto.parking

import com.google.gson.annotations.SerializedName

data class ParkingSpotsResponseDto(
    @SerializedName("spots")
    val spots: List<ParkingSpotDto>,
    @SerializedName("count")
    val count: Int
)

data class ParkingSpotDto(
    @SerializedName("_id")
    val id: String,
    @SerializedName("ownerId")
    val ownerId: OwnerInfoDto?,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("location")
    val location: LocationDto,
    @SerializedName("address")
    val address: AddressDto,
    @SerializedName("pricing")
    val pricing: PricingDto,
    @SerializedName("features")
    val features: List<String>,
    @SerializedName("photos")
    val photos: List<String>,
    @SerializedName("availability")
    val availability: Boolean,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("reviewCount")
    val reviewCount: Int,
    @SerializedName("totalReservations")
    val totalReservations: Int,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)

data class LocationDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("coordinates")
    val coordinates: List<Double> // [longitude, latitude]
)

data class AddressDto(
    @SerializedName("street")
    val street: String?,
    @SerializedName("district")
    val district: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("postalCode")
    val postalCode: String?,
    @SerializedName("fullAddress")
    val fullAddress: String
)

data class PricingDto(
    @SerializedName("hourly")
    val hourly: Double,
    @SerializedName("daily")
    val daily: Double,
    @SerializedName("monthly")
    val monthly: Double
)

data class OwnerInfoDto(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("reviewCount")
    val reviewCount: Int,
    @SerializedName("phone")
    val phone: String?
)