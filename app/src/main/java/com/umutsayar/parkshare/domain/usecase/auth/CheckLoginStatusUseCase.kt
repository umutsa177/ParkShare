package com.umutsayar.parkshare.domain.usecase.auth

import javax.inject.Inject

class CheckLoginStatusUseCase @Inject constructor(
    private val tokenManager: com.umutsayar.parkshare.data.local.TokenManager
) {
    suspend operator fun invoke(): Boolean {
        return tokenManager.isLoggedIn()
    }
}