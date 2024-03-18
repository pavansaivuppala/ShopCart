package com.example.shopcart.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shopcart.CategoryPresenter
import com.example.shopcart.MVPCategory
import com.example.shopcart.databinding.FragmentCategoryBinding
import com.example.shopcart.model.CategoryAdapter
import com.example.shopcart.model.CategoryData
import com.example.shopcart.model.CategoryResponse
import com.example.shopcart.model.VolleyCategoryHandler

class CategoryFragment : Fragment(), MVPCategory.CategoryView {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var presenter: CategoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCategoryBinding.inflate(layoutInflater, container, false)

        presenter= CategoryPresenter(VolleyCategoryHandler(this.context), this)
        presenter.fetchCategoryData()


        return binding.root
    }

    override fun setResultCategory(categoryResponse: CategoryResponse) {
        Log.d("success", categoryResponse.toString())
            val categories = ArrayList<CategoryData>()
            val response = categoryResponse.categories
            for (i in response.indices) {
                categories.add(
                    CategoryData(
                        response[i].category_name.toString(),
                        response[i].category_image_url

                    )
                )
            }
            val categoryAdapter = CategoryAdapter(requireContext(), categories)
            binding.recyclerViewCategory.adapter = categoryAdapter

    }

    override fun showErrorCategory(message: String) {
        Log.d("Failure", message)
    }
    /*
    override fun onItemClick(position: Int) {
        when(position){
            position ->  Toast.makeText(context, "Clicked mobiles ", Toast.LENGTH_SHORT).show()
        }
    }*/


}