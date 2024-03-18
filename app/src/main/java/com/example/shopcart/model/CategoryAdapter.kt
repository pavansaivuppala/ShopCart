package com.example.shopcart.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopcart.model.Constant.BASE_URL
import com.example.shopcart.databinding.CustomCategoryBinding
import com.squareup.picasso.Picasso

class CategoryAdapter(context: Context,val categoryList: ArrayList<CategoryData>):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(private val binding: CustomCategoryBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(categoryList: CategoryData){
            binding.categoryText.text=categoryList.categoryName
            var url=BASE_URL+categoryList.categoryImage
           Picasso.get().load(url.toString()).into(binding.categoryImage)
        }
    }
    /*
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }*/

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding= CustomCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = categoryList[position]
        holder.bind(currentCategory)

    }
}