package com.umutsayar.parkshare.presentation.auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutsayar.parkshare.data.local.TokenManager
import com.umutsayar.parkshare.domain.model.AuthResult
import com.umutsayar.parkshare.domain.model.Resource
import com.umutsayar.parkshare.domain.model.UserRole
import com.umutsayar.parkshare.domain.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val authResult: AuthResult? = null
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String,
        role: UserRole
    ) {
        viewModelScope.launch {
            registerUseCase(
                name = name,
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                phone = phone,
                role = role
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = RegisterState(isLoading = true)
                    }

                    is Resource.Success -> {
                        // Token ve user bilgilerini kaydet
                        tokenManager.saveToken(result.data.token)
                        tokenManager.saveUserInfo(
                            userId = result.data.user.id,
                            role = result.data.user.role.toApiString()
                        )

                        _state.value = RegisterState(
                            isLoading = false,
                            isSuccess = true,
                            authResult = result.data
                        )
                    }

                    is Resource.Error -> {
                        _state.value = RegisterState(
                            isLoading = false,
                            isSuccess = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }

    fun resetState() {
        _state.value = RegisterState()
    }
}