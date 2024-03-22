package com.example.shopcart.model.productdetails

data class ProductDetailResponse(
    val message: String,
    val product: ProductD,
    val status: Int
)