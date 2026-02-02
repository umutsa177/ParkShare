package com.umutsayar.parkshare.domain.usecase.auth

import com.umutsayar.parkshare.domain.model.AuthResult
import com.umutsayar.parkshare.domain.model.Resource
import com.umutsayar.parkshare.domain.model.UserRole
import com.umutsayar.parkshare.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String,
        role: UserRole
    ): Flow<Resource<AuthResult>> {
        // Validasyon
        if (name.isBlank()) {
            return kotlinx.coroutines.flow.flow {
                emit(Resource.Error("İsim boş olamaz"))
            }
        }

        if (name.length < 2) {
            return kotlinx.coroutines.flow.flow {
                emit(Resource.Error("İsim en az 2 karakter olmalıdır"))
            }
        }

        if (email.isBlank()) {
            return kotlinx.coroutines.flow.flow {
                emit(Resource.Error("Email boş olamaz"))
            }
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return kotlinx.coroutines.flow.flow {
                emit(Resource.Error("Geçerli bir email adresi giriniz"))
            }
        }

        if (password.isBlank()) {
            return kotlinx.coroutines.flow.flow {
                emit(Resource.Error("Şifre boş olamaz"))
            }
        }

        if (password.length < 6) {
            return kotlinx.coroutines.flow.flow {
                emit(Resource.Error("Şifre en az 6 karakter olmalıdır"))
            }
        }

        if (password != confirmPassword) {
            return kotlinx.coroutines.flow.flow {
                emit(Resource.Error("Şifreler eşleşmiyor"))
            }
        }

        if (phone.isBlank()) {
            return kotlinx.coroutines.flow.flow {
                emit(Resource.Error("Telefon numarası boş olamaz"))
            }
        }

        // Telefon numarası formatı kontrolü (Türkiye)
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        if (cleanPhone.length != 11 && cleanPhone.length != 10) {
            return kotlinx.coroutines.flow.flow {
                emit(Resource.Error("Geçerli bir telefon numarası giriniz"))
            }
        }

        return authRepository.register(name, email, password, phone, role)
    }
}