package com.example.shopcart.model

interface OperationalCallback {
    fun onSuccess(data: Any)
    fun onFailure(message: String)
}
