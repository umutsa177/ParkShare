package com.umutsayar.parkshare.presentation.auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutsayar.parkshare.data.local.TokenManager
import com.umutsayar.parkshare.domain.model.AuthResult
import com.umutsayar.parkshare.domain.model.Resource
import com.umutsayar.parkshare.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val authResult: AuthResult? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = LoginState(isLoading = true)
                    }

                    is Resource.Success -> {
                        // Token ve user bilgilerini kaydet
                        tokenManager.saveToken(result.data.token)
                        tokenManager.saveUserInfo(
                            userId = result.data.user.id,
                            role = result.data.user.role.toApiString()
                        )

                        _state.value = LoginState(
                            isLoading = false,
                            isSuccess = true,
                            authResult = result.data
                        )
                    }

                    is Resource.Error -> {
                        _state.value = LoginState(
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
        _state.value = LoginState()
    }
}
