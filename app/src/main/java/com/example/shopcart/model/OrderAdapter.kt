package com.example.shopcart.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopcart.R
import com.example.shopcart.databinding.ItemCartBinding
import com.example.shopcart.databinding.ItemOrderBinding
import com.example.shopcart.model.local.AppDatabase
import com.example.shopcart.model.local.Cart
import com.example.shopcart.model.local.CartDao

class OrderAdapter(private val list: List<Cart>): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    private lateinit var binding: ItemOrderBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var cartDao: CartDao

    inner class OrderViewHolder(val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            appDatabase = AppDatabase.getAppDatabase(binding.root.context)!!
            cartDao = appDatabase.getCartDao()
        }

        fun bind(cart: Cart, position: Int) {
            binding.apply {
                Glide.with(root.context)
                    .load(Constant.BASE_URL + cart.imageURL)
                    .placeholder(R.drawable.baseline_mobile_friendly_24)
                    .error(R.drawable.baseline_mobile_friendly_24)
                    .fallback(R.drawable.baseline_mobile_friendly_24)
                    .into(imageCartProductOrder)
                productCartNameOrder.text = cart.name
                cartProductPriceOrder.text = "$ ${cart.price}"
                quantityCartOrder.text=cart.quantity.toString()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemOrderBinding.inflate(layoutInflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val cart = list[position]
        holder.bind(cart, position)

        holder.itemView.setOnLongClickListener {
            cartDao.deleteCartById(cart.id)
            notifyDataSetChanged()
            true

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
