package com.example.shopcart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopcart.R
import com.example.shopcart.databinding.FragmentListOrderBinding
import com.example.shopcart.model.CommunicatorOrderList
import com.example.shopcart.model.OrderListAdapter
import com.example.shopcart.model.local.AppDatabase
import com.example.shopcart.model.local.OrderDao

class ListOrderFragment : Fragment(),CommunicatorOrderList{
    private lateinit var binding: FragmentListOrderBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var orderDao: OrderDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentListOrderBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        initDatabase()
        binding.orderListRecycler.layoutManager =
            GridLayoutManager(this.context, 1, GridLayoutManager.VERTICAL, false)
        val list = orderDao.getAllOrder()

        val Adapter = OrderListAdapter(list,this)
        binding.orderListRecycler.adapter = Adapter

        return binding.root
    }
    private fun initDatabase() {
        appDatabase = AppDatabase.getAppDatabase(this.requireContext())!!
        orderDao = appDatabase.getOrderDao()
    }

    override fun sendorderId(id: Long) {
        val fragment = OrderDetailFragment()
        val bundle = Bundle()

        bundle.putLong("ProductId", id)
        fragment.arguments=bundle


        val homeFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? HomeFragment

        homeFragment?.changeHomeFragment(fragment)
    }
}