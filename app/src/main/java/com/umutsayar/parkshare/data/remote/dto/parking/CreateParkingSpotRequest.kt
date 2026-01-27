package com.umutsayar.parkshare.data.remote.dto.parking

import com.google.gson.annotations.SerializedName

data class CreateParkingSpotRequest(
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
    val photos: List<String>
)

data class ParkingSpotResponseDto(
    @SerializedName("spot")
    val spot: ParkingSpotDto
)