package com.example.shopcart.model

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.shopcart.model.categoryresponse.CategoryResponse
import com.example.shopcart.model.Constant.CATEGORY_URL
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VolleyCategoryHandler(private val context: Context?,private val sub_url: String){
    private val requestQueue by lazy { Volley.newRequestQueue(context)}

    fun makeApiCategoryCall(callback: OperationalCallBackCategory){
        val categoryRequest= StringRequest(
            Request.Method.GET,
            CATEGORY_URL,{
                val typeToken=object : TypeToken<CategoryResponse>(){}
                val response= Gson().fromJson(it,typeToken)
                Log.d("tag volley",response.toString())
                callback.onSuccess(response)
            },{
                Log.d("tag volley",it.toString())
                callback.onFailure(it.toString())
            }

        )
        requestQueue.add(categoryRequest)

    }

}