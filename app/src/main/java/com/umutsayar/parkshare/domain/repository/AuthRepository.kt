package com.umutsayar.parkshare.domain.repository

import com.umutsayar.parkshare.domain.model.*
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String,
        phone: String,
        role: UserRole
    ): Flow<Resource<AuthResult>>

    suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>>

    suspend fun getProfile(): Flow<Resource<User>>

    suspend fun updateProfile(
        name: String?,
        phone: String?,
        profileImage: String?
    ): Flow<Resource<User>>
}