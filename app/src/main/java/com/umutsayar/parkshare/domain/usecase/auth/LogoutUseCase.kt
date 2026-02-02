package com.umutsayar.parkshare.domain.usecase.auth

import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val tokenManager: com.umutsayar.parkshare.data.local.TokenManager
) {
    suspend operator fun invoke() {
        tokenManager.clearToken()
    }
}