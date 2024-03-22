package com.example.shopcart.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopcart.R
import com.example.shopcart.databinding.ItemProductDetailImageBinding
import com.example.shopcart.model.Constant.BASE_URL
import com.example.shopcart.model.productdetails.Image

class ProductDetailImageViewPagerAdapter(private val imageList: ArrayList<Image>): RecyclerView.Adapter<ProductDetailImageViewPagerAdapter.ImageViewHolder>() {
    private lateinit var binding: ItemProductDetailImageBinding
    inner class ImageViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind (image: Image) {
            binding.apply {
                Glide.with(view.context)
                    .load(BASE_URL + image.image)
                    .error(R.drawable.ic_launcher_background)
                    .fallback(R.drawable.ic_launcher_background)
                    .into(imageProductDetail)
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemProductDetailImageBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.apply {
            val image = imageList[position]
            bind(image)
        }
    }


}