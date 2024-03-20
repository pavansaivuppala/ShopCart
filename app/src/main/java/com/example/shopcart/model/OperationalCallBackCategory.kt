package com.example.shopcart.model

import com.example.shopcart.model.categoryresponse.CategoryResponse

interface OperationalCallBackCategory {

    fun onSuccess(categoryResponse: CategoryResponse)
    fun onFailure(message: String)

}