package com.example.shopcart.model

import com.example.shopcart.model.Category

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)