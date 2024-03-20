package com.example.shopcart.model.subcategory

data class SubCategoryResponse(
    val message: String,
    val status: Int,
    val subcategories: ArrayList<Subcategory>
)