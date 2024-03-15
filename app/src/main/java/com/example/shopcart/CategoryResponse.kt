package com.example.shopcart

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)