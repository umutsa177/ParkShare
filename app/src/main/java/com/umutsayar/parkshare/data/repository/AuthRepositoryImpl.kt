package com.umutsayar.parkshare.data.repository

import com.umutsayar.parkshare.data.remote.api.auth.AuthService
import com.umutsayar.parkshare.data.remote.dto.auth.*
import com.umutsayar.parkshare.data.remote.dto.profile.UpdateProfileRequest
import com.umutsayar.parkshare.data.remote.mapper.*
import com.umutsayar.parkshare.domain.model.*
import com.umutsayar.parkshare.domain.repository.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthService
) : AuthRepository {

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        phone: String,
        role: UserRole
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.register(
                RegisterRequest(
                    name = name,
                    email = email,
                    password = password,
                    phone = phone,
                    role = role.toApiString()
                )
            )

            if (response.isSuccessful && response.body()?.success == true) {
                val data = response.body()?.data
                if (data != null) {
                    emit(Resource.Success(
                        AuthResult(
                            token = data.token,
                            user = data.user.toDomain()
                        )
                    ))
                } else {
                    emit(Resource.Error("Kayıt başarısız"))
                }
            } else {
                emit(Resource.Error(response.body()?.message ?: "Kayıt başarısız"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Sunucu hatası: ${e.message()}"))
        } catch (e: IOException) {
            emit(Resource.Error("İnternet bağlantısı yok"))
        } catch (e: Exception) {
            emit(Resource.Error("Beklenmeyen hata: ${e.message}"))
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.login(
                LoginRequest(email, password)
            )

            if (response.isSuccessful && response.body()?.success == true) {
                val data = response.body()?.data
                if (data != null) {
                    emit(Resource.Success(
                        AuthResult(
                            token = data.token,
                            user = data.user.toDomain()
                        )
                    ))
                } else {
                    emit(Resource.Error("Giriş başarısız"))
                }
            } else {
                emit(Resource.Error(response.body()?.message ?: "Giriş başarısız"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Sunucu hatası: ${e.message()}"))
        } catch (e: IOException) {
            emit(Resource.Error("İnternet bağlantısı yok"))
        } catch (e: Exception) {
            emit(Resource.Error("Beklenmeyen hata: ${e.message}"))
        }
    }

    override suspend fun getProfile(): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getProfile()

            if (response.isSuccessful && response.body()?.success == true) {
                val user = response.body()?.data?.user
                if (user != null) {
                    emit(Resource.Success(user.toDomain()))
                } else {
                    emit(Resource.Error("Profil bilgisi alınamadı"))
                }
            } else {
                emit(Resource.Error(response.body()?.message ?: "Profil alınamadı"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun updateProfile(
        name: String?,
        phone: String?,
        profileImage: String?
    ): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.updateProfile(
                UpdateProfileRequest(name, phone, profileImage)
            )

            if (response.isSuccessful && response.body()?.success == true) {
                val user = response.body()?.data?.user
                if (user != null) {
                    emit(Resource.Success(user.toDomain()))
                } else {
                    emit(Resource.Error("Profil güncellenemedi"))
                }
            } else {
                emit(Resource.Error(response.body()?.message ?: "Güncelleme başarısız"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }
}

// =====================