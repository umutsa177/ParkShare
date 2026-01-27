package com.umutsayar.parkshare.domain.model

data class AuthResult(
    val token: String,
    val user: User
)