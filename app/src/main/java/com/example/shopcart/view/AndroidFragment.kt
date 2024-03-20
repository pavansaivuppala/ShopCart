package com.example.shopcart.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopcart.Presenter.SubCategoryPresenter
import com.example.shopcart.databinding.FragmentAndroidBinding
import com.example.shopcart.model.subcategory.Product
import com.example.shopcart.Presenter.MVPSubCategory
import com.example.shopcart.R
import com.example.shopcart.model.Constant.SUB_CATEGORY_ID
import com.example.shopcart.model.SubCategoryVolleyHandler
import com.example.shopcart.model.subcategory.CommunicatorProduct
import com.example.shopcart.model.subcategory.SubCategoryAdapter
import com.example.shopcart.model.subcategory.SubCategoryProductResponse


class AndroidFragment : Fragment(), MVPSubCategory.SubCategoryView,CommunicatorProduct {
    private lateinit var binding: FragmentAndroidBinding
    private lateinit var subCategoryId: String
    private lateinit var presenter: SubCategoryPresenter
    private lateinit var productList: List<Product>
    private lateinit var adapter: SubCategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAndroidBinding.inflate(layoutInflater, container, false)
        subCategoryId = arguments?.getString(SUB_CATEGORY_ID).toString()
        Log.d("hello",subCategoryId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEvents(view)
    }

    private fun setUpEvents(view: View) {
        presenter = SubCategoryPresenter(SubCategoryVolleyHandler(view.context), this)
        presenter.loadSubCategoryProducts(subCategoryId)
    }

    override fun setResult(data: Any) {
        productList = (data as SubCategoryProductResponse).products
        adapter = SubCategoryAdapter(productList,this)
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewProduct.adapter = adapter
        if (productList.isEmpty()) {
            binding.noItem.visibility = View.VISIBLE
        } else {
            binding.noItem.visibility = View.GONE
        }
    }

    override fun sendCode(id: String) {
        val fragment = ProductFragment()
        val bundle = Bundle()
        bundle.putString("URL", id)
        fragment.arguments=bundle


        val homeFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? HomeFragment

        homeFragment?.changeHomeFragment(fragment)
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}