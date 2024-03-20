package com.example.shopcart.model

interface RegisterOperationalCallBack {
    fun onSuccessRegistration(status: Int, response: String)
    fun onFailureRegistration(status: Int, error: String)
}
