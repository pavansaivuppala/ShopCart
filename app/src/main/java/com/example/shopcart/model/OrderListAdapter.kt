package com.example.shopcart.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopcart.databinding.ItemOrderListBinding
import com.example.shopcart.model.local.AppDatabase
import com.example.shopcart.model.local.Order
import com.example.shopcart.model.local.OrderDao

class OrderListAdapter(private val list: List<Order>, private val orderCommunicator : CommunicatorOrderList): RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>() {
    private lateinit var binding: ItemOrderListBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var orderDao: OrderDao

    inner class OrderListViewHolder(val binding: ItemOrderListBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            appDatabase = AppDatabase.getAppDatabase(binding.root.context)!!
            orderDao = appDatabase.getOrderDao()
        }

        fun bind(order: Order) {
            binding.apply {
                IdOrderList.text = order.orderid.toString()
                productCartNameOrderList.text = order.orderDetails
                cartProductPriceOrderList.text=order.price

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemOrderListBinding.inflate(layoutInflater, parent, false)
        return OrderListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
        val order = list[position]
        holder.bind(order)
        holder.itemView.setOnClickListener {
            val number = order.orderid
            orderCommunicator.sendorderId(number)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
