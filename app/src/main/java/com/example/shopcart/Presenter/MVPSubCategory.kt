package com.example.shopcart.Presenter

interface MVPSubCategory {

        interface SubCategoryPresenter {
            fun loadSubCategoryList(categoryId: String): String
            fun loadSubCategoryProducts(subCategoryId: String): String
        }

        interface SubCategoryView {
            fun setResult(data: Any)
            fun onLoad(isLoading: Boolean)
        }
}