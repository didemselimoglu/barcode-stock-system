package com.didem.barcodeapp.data.repository

import com.didem.barcodeapp.data.model.Product
import com.didem.barcodeapp.network.RetrofitClient

class ProductRepository {

    //ürün bulmak için fonksiyon tanımlama
    suspend fun getProductByBarcode(barcodeCode: String, token: String): Result<Product> {
        return try {
            val response = RetrofitClient.apiService.getProductByBarcode(barcodeCode, "Bearer $token")
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Ürün bulunamadı"))
            } else {
                Result.failure(Exception("API hatası: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}