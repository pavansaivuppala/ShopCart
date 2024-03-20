package com.example.shopcart.Presenter

import com.example.shopcart.model.OperationalCallback
import com.example.shopcart.model.ProductVolleyHandler
import com.example.shopcart.model.productdetails.ProductDetailResponse


class ProductDetailsPresenter(private val volleyHandler: ProductVolleyHandler, private val productDetailView: ProductDetailMVP.ProductDetailView): ProductDetailMVP.ProductDetailPresenter {

    override fun getProductDetail(productId: String): String {
        productDetailView.onLoad(true)
        val message = volleyHandler.callProductDetailApiCall(productId,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    productDetailView.setResult(data as ProductDetailResponse, true)
                    productDetailView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    productDetailView.setResult(message, false)
                    productDetailView.onLoad(false)
                }

            })
        return message
    }


}