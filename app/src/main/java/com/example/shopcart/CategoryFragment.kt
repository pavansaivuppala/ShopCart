package com.example.shopcart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopcart.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment(), MVPCategory.CategoryView {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var presenter: CategoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCategoryBinding.inflate(layoutInflater,container,false)

        presenter= CategoryPresenter(VolleyCategoryHandler(this.context),this)
        presenter.fetchCategoryData()

        binding.recyclerViewCategory.layoutManager=GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
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
        Log.d("Failure",message)
    }


}