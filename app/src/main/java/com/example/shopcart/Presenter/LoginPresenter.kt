package com.example.shopcart.Presenter

import com.example.shopcart.model.LoginData
import com.example.shopcart.model.LoginOperationalCallback
import com.example.shopcart.model.UserVolleyHandler

class LoginPresenter(private val volleyHandler: UserVolleyHandler, private val loginView: MVPLogin.LoginView):MVPLogin.LoginPresenter {
    override fun sendLoginData(data: LoginData) {
        volleyHandler.postLogin(data.email_id,data.password,object: LoginOperationalCallback {
            override fun onSuccessLogin(status: Int, message: String) {
                loginView.setResultLogin(0,message)
            }

            override fun onFailureLogin(status: Int, error: String) {
                loginView.setResultLogin(1,error)
            }
        })
    }
}