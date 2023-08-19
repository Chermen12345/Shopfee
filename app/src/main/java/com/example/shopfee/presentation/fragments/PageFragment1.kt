package com.example.shopfee.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentPage1Binding
import com.example.shopfee.presentation.activities.HomeActivity
import com.example.utils.Consts
import kotlinx.coroutines.launch


class PageFragment1 : Fragment() {

    private lateinit var binding: FragmentPage1Binding

    var pager: ViewPager2 ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPage1Binding.inflate(inflater)

        pager = activity?.findViewById<ViewPager2>(R.id.pager)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        changePage()

        skipOnBoarding()



    }
    //view paging from 1 to 2 fragment
    private fun changePage(){
        binding.btpage2.setOnClickListener {

            pager?.currentItem = 1

        }

    }



    //here if user clicks to skip on boarding he navigates to login fragment and we save
    //and we save boolean with value = true to shared pref not to show onBoarding every time
    //user opens the app
    private fun skipOnBoarding(){

        binding.btskiponboard.setOnClickListener {



            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }

    }



}