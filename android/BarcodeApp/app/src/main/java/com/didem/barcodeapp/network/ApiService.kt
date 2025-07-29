//retrofit arayüzü : get post endpoint'leri tanımlama
package com.didem.barcodeapp.network

import com.didem.barcodeapp.data.model.LoginRequest
import com.didem.barcodeapp.data.model.LoginResponse
import com.didem.barcodeapp.data.model.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //kullanıcı girişi için post
    @POST("authenticate")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    //barkodla ürün bulmak için get
    @GET("product/{barcodeCode}")
    suspend fun getProductByBarcode(
        @Path("barcodeCode") barcodeCode: String,
        @Header("Authorization") token: String
    ): Response<Product>

}