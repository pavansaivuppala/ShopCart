package com.example.shopcart

class CategoryPresenter(
    private val volleyCategoryHandler: VolleyCategoryHandler,
    private val categoryView: MVPCategory.CategoryView
): MVPCategory.CategoryPresenter {
    override fun fetchCategoryData(){
        volleyCategoryHandler.makeApiCategoryCall(object : OperationalCallBackCategory{
            override fun onSuccess(categoryResponse: CategoryResponse) {
                categoryView.setResultCategory(categoryResponse)
            }

            override fun onFailure(message: String) {
                categoryView.showErrorCategory(message)
            }
        })
    }
}