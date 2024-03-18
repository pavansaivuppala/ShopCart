package com.example.shopcart.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shopcart.R
import com.example.shopcart.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLoginBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        initPref()

        binding.login.setOnClickListener {
            fetchDetails()
        }
        binding.signUp1.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainerView,
                RegisterFragment()
            ).commit()
            sharedPreferences.edit().putString("fragment","register").apply()
        }
        return binding.root
    }

    private fun fetchDetails() {
        val email=binding.email.getText().toString()
        val password=binding.password.getText().toString()

        val emailverf=sharedPreferences.getString("email","pavan")
        val passverf=sharedPreferences.getString("pass","pass")

        if(email == emailverf && password==passverf){
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainerView,
                HomeFragment()
            ).commit()
            sharedPreferences.edit().putString("fragment","home").apply()
        }
        else{
            binding.email.text?.clear()
            binding.password.text?.clear()
            Toast.makeText(context, "incorrect Details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initPref(){
        sharedPreferences = requireActivity().getSharedPreferences("Login Details",
            Context.MODE_PRIVATE
        )
    }

}