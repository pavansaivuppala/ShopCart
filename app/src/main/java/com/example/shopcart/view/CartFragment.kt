package com.example.shopcart.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopcart.R
import com.example.shopcart.databinding.FragmentCartBinding
import com.example.shopcart.model.CartAdapter
import com.example.shopcart.model.CommunicatorAddToCart
import com.example.shopcart.model.local.AppDatabase
import com.example.shopcart.model.local.CartDao
import java.lang.Appendable

class CartFragment : Fragment(), CommunicatorAddToCart {
    private lateinit var binding: FragmentCartBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var cartDao: CartDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCartBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        initDatabase()
        initView()
        return binding.root
    }

    private fun initView() {
        binding.btnOrder.setOnClickListener{
            val fragment = OrdersFragment()
            val homeFragment =
                requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? HomeFragment

            homeFragment?.changeHomeFragment(fragment)

        }
        binding.recyclerViewCart.layoutManager =
            GridLayoutManager(this.context, 1, GridLayoutManager.VERTICAL, false)
        val data= cartDao.getAllCart()
        val cartadapter=CartAdapter(ArrayList(data),this)

        binding.recyclerViewCart.adapter=cartadapter

    }

    private fun initDatabase() {
        appDatabase = AppDatabase.getAppDatabase(this.requireContext())!!
        cartDao=appDatabase.getCartDao()
    }
    override fun sendFragmentAddToCart() {
        val fragment = CartFragment()
        val homeFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? HomeFragment

        homeFragment?.changeHomeFragment(fragment)
    }

}