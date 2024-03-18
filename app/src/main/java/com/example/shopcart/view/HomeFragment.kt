package com.example.shopcart.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shopcart.R
import com.example.shopcart.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater, container, false)
        changeHomeFragment(CategoryFragment())
        binding.homeMenu.setOnNavigationItemReselectedListener {
            menuItem ->
            when(menuItem.itemId){
                R.id.categoryMenu -> changeHomeFragment(CategoryFragment())
                R.id.profileMenu -> changeHomeFragment(ProfileFragment())
                R.id.cartMenu -> changeHomeFragment(CartFragment())
            }
        }

        return binding.root
    }
    private fun changeHomeFragment(fragment: Fragment){
        childFragmentManager.beginTransaction().replace(R.id.containerHome,fragment).commit()
    }

}