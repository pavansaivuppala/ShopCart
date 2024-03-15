package com.example.shopcart

interface OperationalCallBackCategory {

    fun onSuccess(categoryResponse: CategoryResponse)
    fun onFailure(message: String)

}