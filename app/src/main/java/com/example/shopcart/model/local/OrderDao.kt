package com.example.shopcart.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {
    @Query("SELECT * FROM Orders")
    fun getAllOrder(): List<Order>

    @Insert
    fun insertorder(order: Order)

    @Delete
    fun cancelOrder(order: Order)

    @Query("SELECT * FROM Orders WHERE orderId = :orderId")
    fun getOrderById(orderId: Int): Order?

}