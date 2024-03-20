package com.example.shopcart.model.categoryresponse

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)