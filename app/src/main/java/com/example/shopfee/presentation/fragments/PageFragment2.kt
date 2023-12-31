package com.example.shopfee.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentPage2Binding


class PageFragment2 : Fragment() {


    private lateinit var binding: FragmentPage2Binding

    var pager: ViewPager2 ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPage2Binding.inflate(inflater)
        pager = activity?.findViewById(R.id.pager)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goToPage3()

        skipOnBoarding()
    }

    //view paging from 2 to 3 fragment
    private fun goToPage3(){

        binding.btpage3.setOnClickListener {

            pager?.currentItem = 3
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