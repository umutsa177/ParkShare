package com.umutsayar.parkshare.data.remote.dto.review

import com.google.gson.annotations.SerializedName

data class CreateReviewRequest(
    @SerializedName("reservationId")
    val reservationId: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("comment")
    val comment: String
)