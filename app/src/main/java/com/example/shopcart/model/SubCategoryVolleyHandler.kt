package com.example.shopcart.model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.shopcart.model.Constant.SUB_CATEGORY_PRODUCTS_URL
import com.example.shopcart.model.Constant.SUB_CATEGORY_URL
import com.example.shopcart.model.subcategory.SubCategoryProductResponse
import com.example.shopcart.model.subcategory.SubCategoryResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SubCategoryVolleyHandler(private val context: Context) {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun callSubCategoryListApi(value_of_category_id: String, callback: OperationalCallback): String {
        val url = SUB_CATEGORY_URL + value_of_category_id
        var message: String? = null


        val request = StringRequest(
            Request.Method.GET, url,
            Response.Listener {
                val typeToken=object : TypeToken<SubCategoryResponse>(){}
                val subCategoryListResponse= Gson().fromJson(it,typeToken)
                callback.onSuccess(subCategoryListResponse)
            }) {
            message = it.toString()
            callback.onFailure(it.toString())
        }

        requestQueue.add(request)
        return message.toString()
    }

    fun callSubCategoryProductListApi(sub_category_id: String, callback: OperationalCallback): String {
        Log.e("subcatval",sub_category_id)
        val url = SUB_CATEGORY_PRODUCTS_URL + sub_category_id
        var message: String? = null

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener {
                val typeToken1=object : TypeToken<SubCategoryProductResponse>(){}
                val subCategoryProductListResponse= Gson().fromJson(it,typeToken1)
                Log.e("subcatvalproduct",subCategoryProductListResponse.toString())

                callback.onSuccess(subCategoryProductListResponse)
            }) {
            message = it.toString()
            callback.onFailure(it.toString())
        }
        requestQueue.add(request)
        return message.toString()
    }

}