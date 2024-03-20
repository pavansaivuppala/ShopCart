package com.example.shopcart.Presenter

import com.example.shopcart.model.UserData


interface RegisterMVP {
    interface RegisterPresenter{
        fun sendRegisterData(data: UserData)
    }
    interface RegisterView{
        fun setResultRegister(status:Int, message:String)
    }
}