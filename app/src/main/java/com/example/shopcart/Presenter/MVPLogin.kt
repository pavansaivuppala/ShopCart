package com.example.shopcart.Presenter

import com.example.shopcart.model.LoginData

interface MVPLogin {
    interface LoginPresenter{
        fun sendLoginData(data: LoginData)
    }
    interface LoginView{
        fun setResultLogin(status:Int, message:String)
    }
}