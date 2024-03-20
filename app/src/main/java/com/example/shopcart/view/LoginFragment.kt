package com.example.shopcart.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shopcart.Presenter.LoginPresenter
import com.example.shopcart.Presenter.MVPLogin
import com.example.shopcart.R
import com.example.shopcart.databinding.FragmentLoginBinding
import com.example.shopcart.model.LoginData

class LoginFragment : Fragment(), MVPLogin.LoginView {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var presenter: LoginPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        initPref()

        binding.login.setOnClickListener {
            fetchDetails()
        }
        binding.signUp1.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainerView,
                RegisterFragment()
            ).commit()
            sharedPref.edit().putString("fragment", "register").apply()
        }
        return binding.root
    }

    private fun fetchDetails() {
        val emailFromReg = sharedPref.getString("email", "pavan")
        val passwordfromReg = sharedPref.getString("password", "pass")
        val email = binding.email.getText().toString()
        val password = binding.password.getText().toString()
        if ((email == emailFromReg) && (password == passwordfromReg)) {
            navigateToHome()
            isLoggedIn()
            //presenter.sendLoginData(LoginData(email, password))
        } else {
            Toast.makeText(context, "Userid or password incorrect", Toast.LENGTH_SHORT).show()
        }
    }


    private fun initPref() {
        sharedPref = requireActivity().getSharedPreferences(
            "Register",
            Context.MODE_PRIVATE
        )
    }
    override fun setResultLogin(status: Int, message: String) {
        if(status==0){
            showToast("Logged in Successfully")
        }
        else
        {
            showToast("Email id or password incorrect")
        }
    }



    private fun isLoggedIn() {
        sharedPref.edit().putString("fragment", "home").apply()
    }

    private fun navigateToHome() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, HomeFragment())
            .commit()
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
