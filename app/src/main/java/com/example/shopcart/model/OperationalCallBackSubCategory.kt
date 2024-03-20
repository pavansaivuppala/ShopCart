package com.example.shopcart.model

import com.example.shopcart.model.subcategory.SubCategoryResponse

interface OperationalCallBackSubCategory {
    fun onSuccessSub(subCategoryResponse: SubCategoryResponse)
    fun onFailureSub(message: String)
}