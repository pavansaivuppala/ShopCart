package com.example.shopcart

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.shopcart.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding:FragmentRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding=FragmentRegisterBinding.inflate(layoutInflater,container,false)
        initPref()

        binding.register.setOnClickListener{
            getDetails()
        }

        binding.login1.setOnClickListener{
            sharedPreferences.edit().putString("fragment","login").apply()
            requireActivity().supportFragmentManager.popBackStack()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,LoginFragment()).commit()
        }
        return binding.root
    }

    private fun getDetails(){
        val email=binding.email.getText().toString()
        val name=binding.name.getText().toString()
        val pass=binding.password.getText().toString()
        val repass=binding.repassword.getText().toString()
        if(pass == repass){
            sharedPreferences.edit().clear().apply()
            sharedPreferences.edit().putString("email",email).apply()
            sharedPreferences.edit().putString("name",name).apply()
            sharedPreferences.edit().putString("pass",pass).apply()
            sharedPreferences.edit().putString("repass",pass).apply()
            sharedPreferences.edit().putString("fragment","home").apply()
            Toast.makeText(context,"success",Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,HomeFragment()).commit()
        }else{
            Toast.makeText(context,"Enter Matching passwords",Toast.LENGTH_SHORT).show()
            binding.name.text?.clear()
            binding.email.text?.clear()
            binding.password.text?.clear()
            binding.repassword.text?.clear()
        }

    }
    private fun initPref(){
        sharedPreferences = requireActivity().getSharedPreferences("Login Details", Context.MODE_PRIVATE)

    }


}