package com.didem.barcodeapp.viewmodel
// ViewModel sınıfı: UI ile veri katmanı arasındaki köprü

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.didem.barcodeapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    //değişkenleri tanımladım
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    //update fonksiyonu: gelen değişken değerlerini state'e aktarabilmek için
    fun updateUsername(newUsername: String){
        username = newUsername
    }
    fun updatePassword(newPassword: String){
        password = newPassword
    }

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    // kullanıcı login ekranında giriş yap butonuna bastığında bu fonksiyon çalışır
    fun login() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            // repository üzerinden login işlemi
            val result = authRepository.login(username, password)

            result
                .onSuccess { token ->
                    _loginState.value = LoginState.Success(token)
                }
                .onFailure { error ->
                    // Hata mesajı ekledim
                    val message = error.message ?: "Kullanıcı Adı veya Şifre Hatalı"
                    _loginState.value = LoginState.Error(message)
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