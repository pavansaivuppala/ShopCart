package com.example.shopcart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopcart.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding:FragmentRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentRegisterBinding.inflate(layoutInflater,container,false)

        binding.login1.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,LoginFragment()).commit()
        }
        return binding.root
    }

}