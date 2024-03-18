package com.example.shopcart

import com.example.shopcart.model.CategoryResponse
import com.example.shopcart.model.OperationalCallBackCategory
import com.example.shopcart.model.VolleyCategoryHandler

class CategoryPresenter(
    private val volleyCategoryHandler: VolleyCategoryHandler,
    private val categoryView: MVPCategory.CategoryView
): MVPCategory.CategoryPresenter {
    override fun fetchCategoryData(){
        volleyCategoryHandler.makeApiCategoryCall(object : OperationalCallBackCategory {
            override fun onSuccess(categoryResponse: CategoryResponse) {
                categoryView.setResultCategory(categoryResponse)
            }

            override fun onFailure(message: String) {
                categoryView.showErrorCategory(message)
            }
        })
    }
}