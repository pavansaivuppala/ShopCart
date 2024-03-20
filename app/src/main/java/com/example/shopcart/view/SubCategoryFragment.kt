package com.example.shopcart.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shopcart.Presenter.MVPSubCategory
import com.example.shopcart.Presenter.SubCategoryPresenter
import com.example.shopcart.databinding.FragmentSubCategoryBinding
import com.example.shopcart.model.SubCategoryVolleyHandler
import com.example.shopcart.model.ViewPagerAdapter
import com.example.shopcart.model.subcategory.SubCategoryResponse
import com.example.shopcart.model.subcategory.Subcategory
import com.google.android.material.tabs.TabLayoutMediator

class SubCategoryFragment : Fragment(), MVPSubCategory.SubCategoryView {
    private lateinit var binding: FragmentSubCategoryBinding
    private lateinit var presenter: SubCategoryPresenter
    private lateinit var subCategoryList: ArrayList<Subcategory>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubCategoryBinding.inflate(layoutInflater, container, false)
        val bundle = arguments
        val data = bundle?.getString("URL").toString()
        getSubCategoryList(data)

        return binding.root
    }

    private fun getSubCategoryList(categoryId: String) {
        presenter = SubCategoryPresenter(SubCategoryVolleyHandler(requireActivity()), this)
        presenter.loadSubCategoryList(categoryId)
    }

    private fun setUpViewPager() {
        val adapter = ViewPagerAdapter(requireActivity(), subCategoryList)
        binding.viewpager.adapter = adapter
    }

    private fun setUpTabLayout() {
        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            tab.text = subCategoryList[position].subcategory_name
        }.attach()
    }

    override fun setResult(data: Any) {
        if (data is SubCategoryResponse) {
            subCategoryList = data.subcategories
            Log.e("SubCategoryFragment", data.toString())

        } else {
            Log.e("SubCategoryFragment", "Unexpected data type: ${data.javaClass.simpleName}")
        }
        setUpViewPager()
        setUpTabLayout()
    }

    override fun onLoad(isLoading: Boolean) {
        Log.i("tag", isLoading.toString())
    }
}
