package com.didem.barcodeapp.viewmodel
// ViewModel sınıfı: UI ile veri katmanı arasındaki köprü

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.didem.barcodeapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    // kullanıcı login ekranında giriş yap butonuna bastığında bu fonksiyon çalışır
    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            // repository üzerinden login işlemi
            authRepository.login(username, password)
                .onSuccess { token ->
                    _loginState.value = LoginState.Success(token)
                }
                .onFailure { error ->
                    _loginState.value = LoginState.Error(error.message ?: "Bilinmeyen hata")
                }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}