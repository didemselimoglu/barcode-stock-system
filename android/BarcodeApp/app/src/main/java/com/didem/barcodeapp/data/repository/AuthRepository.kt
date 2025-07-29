package com.didem.barcodeapp.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.didem.barcodeapp.data.model.LoginRequest
import com.didem.barcodeapp.network.RetrofitClient

class AuthRepository(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    suspend fun login(username: String, password: String): Result<String>{
        return try {
            val response = RetrofitClient.apiService.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                val token = response.body()?.token
                if (token != null) {
                    saveToken(token)
                    Result.success(token)
                } else {
                    Result.failure(Exception("Token boş"))
                }
            } else {
                Result.failure(Exception("Giriş başarısız"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun saveToken(token: String) {
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove("auth_token").apply()
    }
}