package com.example.shopcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopcart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitViews()
    }

    private fun InitViews() {
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView,LoginFragment()).commit()
        binding.arrowback.setOnClickListener{
            supportFragmentManager.popBackStack()
        }
    }


}