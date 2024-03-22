package com.example.shopcart.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopcart.R
import com.example.shopcart.databinding.ItemCartBinding
import com.example.shopcart.model.local.AppDatabase
import com.example.shopcart.model.local.Cart
import com.example.shopcart.model.local.CartDao

class CartAdapter(private val list: List<Cart>,private val communicatorAddToCart: CommunicatorAddToCart): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private lateinit var binding: ItemCartBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var cartDao: CartDao

    inner class CartViewHolder(val binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root) {
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
                    .into(imageCartProduct)
                productCartName.text = cart.name
                cartProductPrice.text = "$ ${cart.price}"
                quantityCart.setText(cart.quantity.toString())

                UpdateCart.setOnClickListener {
                    val quantity = quantityCart.text.toString()
                    cartDao.updateQuantity(cart.id, quantity)
                    notifyDataSetChanged()
                    communicatorAddToCart.sendFragmentAddToCart()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemCartBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = list[position]
        holder.bind(cart, position)

        holder.itemView.setOnLongClickListener {
            cartDao.deleteCartById(cart.id)
            notifyDataSetChanged()
            communicatorAddToCart.sendFragmentAddToCart()
            true

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
