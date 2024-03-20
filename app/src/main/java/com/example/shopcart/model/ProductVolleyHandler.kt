package com.example.shopcart.model

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.shopcart.model.Constant.PRODUCT_DETAIL_URL
import com.google.gson.Gson
import com.android.volley.Request
import com.android.volley.Response
import com.example.shopcart.model.productdetails.ProductDetailResponse

class ProductVolleyHandler(private val context: Context) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun callProductDetailApiCall(product_id: String, callback: OperationalCallback): String {
        val url = PRODUCT_DETAIL_URL + product_id
        var message: String? = null
        val gson = Gson()


        val request = StringRequest(Request.Method.GET, url,
            Response.Listener {
                message = it.toString()
                val productDetailResponse =
                    gson.fromJson(message, ProductDetailResponse::class.java)
                callback.onSuccess(productDetailResponse)
            }
        ) {
            message = it.toString()
            callback.onFailure(message.toString())
        }
        requestQueue.add(request)
        return message.toString()
    }
}