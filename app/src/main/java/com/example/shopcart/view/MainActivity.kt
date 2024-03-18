package com.example.shopcart.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.shopcart.R
import com.example.shopcart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPref()
        InitViews()
        binding.arrowback.setOnClickListener{
            sharedPreferences.edit().putString("fragment","login")
            supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainerView,
                LoginFragment()
            ).commit()
        }
    }

    private fun InitViews() {
        val values = sharedPreferences.getString("fragment","login")
        when(values){
            "login" -> changeFragment(LoginFragment())
            "register"-> changeFragment(RegisterFragment())
            "home"->  changeFragment(HomeFragment())
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView,fragment).commit()

    }
    private fun initPref(){
        sharedPreferences = getSharedPreferences("Login Details", MODE_PRIVATE)
    }


}