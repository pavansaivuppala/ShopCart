package com.example.shopcart.model

interface LoginOperationalCallback {

    fun onSuccessLogin(status: Int,message:String)
    fun onFailureLogin(status: Int, error: String)
}