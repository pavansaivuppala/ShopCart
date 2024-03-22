package com.example.shopcart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopcart.R
import com.example.shopcart.databinding.FragmentOrdersBinding
import com.example.shopcart.model.CartAdapter
import com.example.shopcart.model.CommunicatorAddToCart
import com.example.shopcart.model.OrderAdapter
import com.example.shopcart.model.local.AppDatabase
import com.example.shopcart.model.local.CartDao
import com.example.shopcart.model.local.Order
import com.example.shopcart.model.local.OrderDao


class OrdersFragment : Fragment(){
    private lateinit var binding: FragmentOrdersBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var cartDao: CartDao
    private lateinit var orderDao: OrderDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentOrdersBinding.inflate(layoutInflater,container,false)

        binding.recyclerCheckout.layoutManager =
            GridLayoutManager(this.context, 1, GridLayoutManager.VERTICAL, false)
        initDatabase()
        val data= cartDao.getAllCart()
        val cartadapter= OrderAdapter(ArrayList(data))

        binding.recyclerCheckout.adapter=cartadapter
        var orderDetails= "/n"
        var price: Int = 0
        for(i in data){
            orderDetails = "${i.name+i.price+i.quantity}" + orderDetails
            price = price + ((i.price?.toInt())!!*(i.quantity!!.toInt()))
        }

            val name = binding.NameOrder.getText().toString()
        val number = binding.PhoneNumber.getText().toString()
        val address = binding.addressOrder.getText().toString()
        val cardnumber = binding.CardNumber.getText().toString()
        val cardname = binding.CardName.getText().toString()
        val cvv = binding.CVV.getText().toString()
        val exp= binding.expiryDate.getText().toString()

        orderDao.insertorder(Order(orderDetails = "orderDetails", orderid = 1, price = price.toString(), personName = name, address = address, cardnumber = cardnumber, cardName = cardname, cvv = cvv, expdate = exp, personNumber = number))


        binding.completeOrder.setOnClickListener{
            Toast.makeText(context,"ORDER SUCCESSFULLY PLACED",Toast.LENGTH_SHORT).show()
            val fragment = ListOrderFragment()
            val homeFragment =
                requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? HomeFragment

            homeFragment?.changeHomeFragment(fragment)
        }

        return binding.root
    }

    private fun initDatabase() {
        appDatabase = AppDatabase.getAppDatabase(this.requireContext())!!
        cartDao=appDatabase.getCartDao()
        orderDao = appDatabase.getOrderDao()
    }



}