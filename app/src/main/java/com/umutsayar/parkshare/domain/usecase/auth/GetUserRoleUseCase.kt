package com.umutsayar.parkshare.domain.usecase.auth

import javax.inject.Inject

class GetUserRoleUseCase @Inject constructor(
    private val tokenManager: com.umutsayar.parkshare.data.local.TokenManager
) {
    operator fun invoke(): kotlinx.coroutines.flow.Flow<String?> {
        return tokenManager.getUserRole()
    }
}