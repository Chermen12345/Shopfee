package com.example.shopfee.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentPage3Binding
import java.util.prefs.Preferences


class PageFragment3 : Fragment() {

    private lateinit var binding: FragmentPage3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPage3Binding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goLogin()

    }

    private fun goLogin(){
        binding.apply {

            btGoToLogin.setOnClickListener {

                findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
            }
        }
    }


}