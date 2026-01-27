package com.umutsayar.parkshare.data.remote.mapper

import com.umutsayar.parkshare.data.remote.dto.user.UserDto
import com.umutsayar.parkshare.domain.model.*

fun UserDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email,
        phone = phone,
        role = UserRole.fromString(role),
        rating = rating,
        reviewCount = reviewCount,
        profileImage = profileImage
    )
}