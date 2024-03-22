package com.example.shopcart.model.subcategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopcart.R
import com.example.shopcart.databinding.ItemProductBinding
import com.example.shopcart.model.CommunicatorSubcategory
import com.example.shopcart.model.Constant.BASE_URL
import com.squareup.picasso.Picasso

class SubCategoryAdapter(private val productList: List<Product>,private val communicatorProduct: CommunicatorProduct): RecyclerView.Adapter<SubCategoryAdapter.ProductViewHolder>() {
    private lateinit var binding: ItemProductBinding

    inner class ProductViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(product: Product) {
            binding.apply {
                Glide.with(view.context)
                    .load(BASE_URL + product.product_image_url)
                    .placeholder(R.drawable.baseline_mobile_friendly_24)
                    .error(R.drawable.baseline_mobile_friendly_24)
                    .fallback(R.drawable.baseline_mobile_friendly_24)
                    .into(imageProduct)
                textProductName.text = product.product_name
                textProductDescription.text = product.description
                textProductPrice.text = "$ ${product.price}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.apply {
            val product = productList[position]
            bind(product)

        }

        holder.itemView.setOnClickListener{
            val p=position+1
            communicatorProduct.sendCode(p.toString())
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}