package com.umutsayar.parkshare.data.remote.dto.auth

import com.google.gson.annotations.SerializedName
import com.umutsayar.parkshare.data.remote.dto.user.UserDto

data class AuthResponseDto(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: UserDto
)