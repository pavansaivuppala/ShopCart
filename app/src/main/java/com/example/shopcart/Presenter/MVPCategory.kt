package com.example.shopcart

import com.example.shopcart.model.CategoryResponse

interface MVPCategory {
    interface CategoryPresenter{
        fun fetchCategoryData()
    }
    interface CategoryView{
        fun setResultCategory(categoryResponse: CategoryResponse)
        fun showErrorCategory(message: String)
    }
}