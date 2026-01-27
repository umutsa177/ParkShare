package com.umutsayar.parkshare.data.remote.dto.reservation

import com.google.gson.annotations.SerializedName

data class UpdateReservationStatusRequest(
    @SerializedName("status")
    val status: String,
    @SerializedName("cancellationReason")
    val cancellationReason: String?
)