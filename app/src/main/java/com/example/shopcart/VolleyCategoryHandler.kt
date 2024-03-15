package com.example.shopcart

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.shopcart.Constant.CATEGORY_URL
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VolleyCategoryHandler(private val context: Context?){
    private val requestQueue by lazy { Volley.newRequestQueue(context)}

    fun makeApiCategoryCall(callback: OperationalCallBackCategory){
        val categoryRequest= StringRequest(
            Request.Method.GET,
            CATEGORY_URL,{
                val typeToken=object : TypeToken<CategoryResponse>(){}
                val response= Gson().fromJson(it,typeToken)
                Log.d("tag",response.toString())
                callback.onSuccess(response)
            },{
                Log.d("tag",it.toString())
                callback.onFailure(it.toString())
            }

        )
        requestQueue.add(categoryRequest)

    }
}