package com.didem.barcodeapp.data.model

data class Product(

    val id: Int,
    val barcodeCode: String,
    val productName: String,
    val unit: String,
    val quantity: String,
    val location: String
)