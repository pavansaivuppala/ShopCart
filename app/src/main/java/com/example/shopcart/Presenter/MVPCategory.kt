package com.example.shopcart
interface MVPCategory {
    interface CategoryPresenter{
        fun fetchCategoryData()
    }
    interface CategoryView{
        fun setResultCategory(categoryResponse: CategoryResponse)
        fun showErrorCategory(message: String)
    }
}