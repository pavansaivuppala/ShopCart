package com.example.shopcart.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopcart.CategoryPresenter
import com.example.shopcart.MVPCategory
import com.example.shopcart.R
import com.example.shopcart.databinding.FragmentCategoryBinding
import com.example.shopcart.model.categoryresponse.CategoryAdapter
import com.example.shopcart.model.categoryresponse.CategoryData
import com.example.shopcart.model.categoryresponse.CategoryResponse
import com.example.shopcart.model.CommunicatorSubcategory
import com.example.shopcart.model.VolleyCategoryHandler

class CategoryFragment : Fragment(), MVPCategory.CategoryView,CommunicatorSubcategory {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var presenter: CategoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)

        presenter = CategoryPresenter(VolleyCategoryHandler(this.context, "hi"), this)
        presenter.fetchCategoryData()
        val span = 2
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
        binding.recyclerViewCategory.layoutManager =
            GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)
        val categoryAdapter = CategoryAdapter(categories, this)
        binding.recyclerViewCategory.adapter = categoryAdapter


    }

    override fun sendSubCategory(id: String) {
        val fragment = SubCategoryFragment()
        val bundle = Bundle()
        bundle.putString("URL", id)
        fragment.arguments=bundle


        val homeFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? HomeFragment

        homeFragment?.changeHomeFragment(fragment)
    }

    override fun showErrorCategory(message: String) {
        Log.d("Failure", message)
    }

}