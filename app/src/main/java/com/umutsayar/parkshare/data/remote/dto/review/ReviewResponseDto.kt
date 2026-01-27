package com.umutsayar.parkshare.data.remote.dto.review

import com.google.gson.annotations.SerializedName
import com.umutsayar.parkshare.data.remote.dto.user.UserDto

data class ReviewsResponseDto(
    @SerializedName("reviews")
    val reviews: List<ReviewDto>,
    @SerializedName("count")
    val count: Int
)

data class ReviewDto(
    @SerializedName("_id")
    val id: String,
    @SerializedName("reservationId")
    val reservationId: String,
    @SerializedName("userId")
    val userId: UserDto?,
    @SerializedName("targetUserId")
    val targetUserId: UserDto?,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("reviewType")
    val reviewType: String, // "spot" or "renter"
    @SerializedName("createdAt")
    val createdAt: String
)