package com.umutsayar.parkshare.data.remote.api.auth

import com.umutsayar.parkshare.data.remote.dto.*
import com.umutsayar.parkshare.data.remote.dto.auth.*
import com.umutsayar.parkshare.data.remote.dto.profile.*
import retrofit2.Response
import retrofit2.http.*

interface AuthService {
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<ApiResponse<AuthResponseDto>>

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<ApiResponse<AuthResponseDto>>

    @GET("auth/profile")
    suspend fun getProfile(): Response<ApiResponse<ProfileResponseDto>>

    @PUT("auth/profile")
    suspend fun updateProfile(
        @Body request: UpdateProfileRequest
    ): Response<ApiResponse<ProfileResponseDto>>
}