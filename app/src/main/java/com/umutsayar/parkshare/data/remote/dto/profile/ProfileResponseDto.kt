package com.umutsayar.parkshare.data.remote.dto.profile

import com.google.gson.annotations.SerializedName
import com.umutsayar.parkshare.data.remote.dto.user.UserDto

data class ProfileResponseDto(
    @SerializedName("user")
    val user: UserDto
)