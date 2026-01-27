package com.umutsayar.parkshare.data.remote.dto.reservation

import com.google.gson.annotations.SerializedName
import com.umutsayar.parkshare.data.remote.dto.parking.ParkingSpotDto
import com.umutsayar.parkshare.data.remote.dto.user.UserDto

data class ReservationsResponseDto(
    @SerializedName("reservations")
    val reservations: List<ReservationDto>,
    @SerializedName("count")
    val count: Int
)

data class ReservationDto(
    @SerializedName("_id")
    val id: String,
    @SerializedName("spotId")
    val spotId: ParkingSpotDto?,
    @SerializedName("renterId")
    val renterId: UserDto?,
    @SerializedName("ownerId")
    val ownerId: UserDto?,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("totalPrice")
    val totalPrice: Double,
    @SerializedName("pricingType")
    val pricingType: String, // "hourly", "daily", "monthly"
    @SerializedName("duration")
    val duration: DurationDto?,
    @SerializedName("status")
    val status: String, // "pending", "confirmed", "active", "completed", "cancelled"
    @SerializedName("cancellationReason")
    val cancellationReason: String?,
    @SerializedName("paymentStatus")
    val paymentStatus: String,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)

data class DurationDto(
    @SerializedName("hours")
    val hours: Int,
    @SerializedName("days")
    val days: Int,
    @SerializedName("months")
    val months: Int
)