package com.umutsayar.parkshare.data.remote.dto.user

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("reviewCount")
    val reviewCount: Int,
    @SerializedName("profileImage")
    val profileImage: String?
)