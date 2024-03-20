package com.example.shopcart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shopcart.R
import com.example.shopcart.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCartBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


}