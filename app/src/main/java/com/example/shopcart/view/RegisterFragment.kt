package com.example.shopcart.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shopcart.Presenter.RegisterMVP
import com.example.shopcart.Presenter.RegisterPresenter
import com.example.shopcart.R
import com.example.shopcart.databinding.FragmentRegisterBinding
import com.example.shopcart.model.UserData
import com.example.shopcart.model.UserVolleyHandler

class RegisterFragment : Fragment(){
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var sharedPref:SharedPreferences
    private lateinit var presenter: RegisterPresenter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentRegisterBinding.inflate(layoutInflater, container, false)
        initPref()
        binding.login1.setOnClickListener {
            sharedPref.edit().putString("fragment", "login").apply()
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainerView,
                LoginFragment()
            ).commit()
        }

        binding.register.setOnClickListener{
            getDetails()
        }

        return binding.root
    }
    private fun getDetails(){
        val email=binding.email.getText().toString()
        val name=binding.name.getText().toString()
        val pass=binding.password.getText().toString()
        val number = binding.number.getText().toString()
        val repass=binding.repassword.getText().toString()
        if(pass != repass) {
            Toast.makeText(requireContext(), "hellohireenterpass", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "hellohi", Toast.LENGTH_SHORT).show()
            sharedPref.edit().putString("name", name).apply()
            sharedPref.edit(). putString("email", email).apply()
            sharedPref.edit().putString("number", number).apply()
            sharedPref.edit().putString("password", pass).apply()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, HomeFragment()).commit()
            sharedPref.edit().putString("fragment","home").apply()
           // presenter= RegisterPresenter(UserVolleyHandler(requireContext()),this)
            //presenter.sendRegisterData(UserData(email,name,number,pass))
        }

    }
    /*override fun setResultRegister(status: Int, message: String) {
        if (status == 0) {
            Toast.makeText(requireContext(), "Registration successfull", Toast.LENGTH_SHORT).show()
            Log.e("registratiom", "success")
        } else {
            Toast.makeText(requireContext(), "Registration failed: $message", Toast.LENGTH_SHORT).show()
            Log.e("registratiom", "Registration failed: $message")
        }
    }*/

    private fun initPref(){
        sharedPref = requireActivity().getSharedPreferences("Register",Context.MODE_PRIVATE)

    }



}