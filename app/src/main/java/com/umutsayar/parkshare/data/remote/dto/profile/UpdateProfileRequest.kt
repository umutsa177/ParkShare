package com.umutsayar.parkshare.data.remote.dto.profile

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("profileImage")
    val profileImage: String?
)