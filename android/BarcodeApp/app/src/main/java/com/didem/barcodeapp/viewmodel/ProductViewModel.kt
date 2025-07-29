package com.didem.barcodeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.didem.barcodeapp.data.repository.ProductRepository
import com.didem.barcodeapp.data.repository.AuthRepository
import com.didem.barcodeapp.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _productState = MutableStateFlow<ProductState>(ProductState.Idle)
    val productState: StateFlow<ProductState> = _productState

    // Barkod kodu ile ürün getir
    fun getProductByBarcode(barcodeCode: String) {
        viewModelScope.launch {
            _productState.value = ProductState.Loading

            val token = authRepository.getToken()
            if (token == null) {
                _productState.value = ProductState.Error("Token bulunamadı, lütfen tekrar giriş yapın")
                return@launch
            }

            productRepository.getProductByBarcode(barcodeCode, token)
                .onSuccess { product ->
                    _productState.value = ProductState.Success(product)
                }
                .onFailure { error ->
                    _productState.value = ProductState.Error(error.message ?: "Ürün arama hatası")
                }
        }
    }

    // State'i sıfırla
    fun resetState() {
        _productState.value = ProductState.Idle
    }
}

// Ürün için state
sealed class ProductState {
    object Idle : ProductState()
    object Loading : ProductState()
    data class Success(val product: Product) : ProductState()
    data class Error(val message: String) : ProductState()
}