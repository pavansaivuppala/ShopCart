package com.example.shopcart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopcart.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCategoryBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        binding.recyclerViewCategory.layoutManager=GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
        val categories= ArrayList<CategoryData>()
        categories.add(CategoryData("Mobile","https://image.tmdb.org/t/p/original/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg"))
        categories.add(CategoryData("Mobile","https://images.dog.ceo/breeds/stbernard/n02109525_12604.jpg"))
        categories.add(CategoryData("Mobile","https://images.dog.ceo/breeds/stbernard/n02109525_12604.jpg"))
        categories.add(CategoryData("Mobile","https://images.dog.ceo/breeds/stbernard/n02109525_12604.jpg"))
        categories.add(CategoryData("Mobile","https://images.dog.ceo/breeds/stbernard/n02109525_12604.jpg"))
        categories.add(CategoryData("Mobile","https://images.dog.ceo/breeds/stbernard/n02109525_12604.jpg"))
        val categoryAdapter=CategoryAdapter(requireContext(),categories)
        binding.recyclerViewCategory.adapter=categoryAdapter

        return binding.root
    }

}