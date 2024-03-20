package com.example.shopcart.Presenter

import com.example.shopcart.model.OperationalCallback
import com.example.shopcart.model.SubCategoryVolleyHandler
import com.example.shopcart.model.subcategory.SubCategoryProductResponse
import com.example.shopcart.model.subcategory.SubCategoryResponse

class SubCategoryPresenter(private val volleyHandler: SubCategoryVolleyHandler, private val subCategoryView: MVPSubCategory.SubCategoryView): MVPSubCategory.SubCategoryPresenter {
    override fun loadSubCategoryList(categoryId: String): String {
        subCategoryView.onLoad(true)
        val message = volleyHandler.callSubCategoryListApi(categoryId,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    subCategoryView.setResult(data as SubCategoryResponse)
                    subCategoryView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    subCategoryView.onLoad(false)
                }
            })
        return message
    }

    override fun loadSubCategoryProducts(subCategoryId: String): String {
        subCategoryView.onLoad(true)
        val message = volleyHandler.callSubCategoryProductListApi(subCategoryId,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    subCategoryView.setResult(data as SubCategoryProductResponse)
                    subCategoryView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    subCategoryView.onLoad(false)
                }
            })
        return message
    }


}