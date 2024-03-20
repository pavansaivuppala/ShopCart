package com.example.shopcart.model.subcategory

data class SubCategoryProductResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)