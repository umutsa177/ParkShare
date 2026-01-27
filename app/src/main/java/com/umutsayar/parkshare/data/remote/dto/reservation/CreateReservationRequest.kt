package com.umutsayar.parkshare.data.remote.dto.reservation

import com.google.gson.annotations.SerializedName

data class CreateReservationRequest(
    @SerializedName("spotId")
    val spotId: String,
    @SerializedName("startDate")
    val startDate: String, // ISO 8601 format
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("pricingType")
    val pricingType: String,
    @SerializedName("notes")
    val notes: String?
)

data class ReservationResponseDto(
    @SerializedName("reservation")
    val reservation: ReservationDto
)