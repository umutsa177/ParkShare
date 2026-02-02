package com.umutsayar.parkshare.domain.usecase.auth

import com.umutsayar.parkshare.domain.model.AuthResult
import com.umutsayar.parkshare.domain.model.Resource
import com.umutsayar.parkshare.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        // Validasyon
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

        return authRepository.login(email, password)
    }
}