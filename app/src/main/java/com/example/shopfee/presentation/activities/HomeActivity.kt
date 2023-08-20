package com.example.shopfee.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shopfee.R
import com.example.shopfee.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setUpHomeNavigation()
    }

    private fun setUpHomeNavigation(){

        binding.btnav.setupWithNavController(findNavController(R.id.navhosthome))
    }
}