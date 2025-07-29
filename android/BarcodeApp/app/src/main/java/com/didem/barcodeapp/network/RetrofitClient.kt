package com.didem.barcodeapp.network

import retrofit2.Retrofit //http istekleri yapmak için kullanılan kütüphane
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.111:8080/" //emülator için: 10.0.2.2:8080

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)//istek atacağımız sunucu (springboot) url'si
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}