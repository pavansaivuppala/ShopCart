package com.example.shopcart.model.productdetails

data class ProductDetailResponse(
    val message: String,
    val product: Product,
    val status: Int
)