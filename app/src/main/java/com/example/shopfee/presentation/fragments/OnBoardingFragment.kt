package com.example.shopfee.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentOnBoardingBinding
import com.example.shopfee.presentation.adapters.ViewPagerOnBoarding


class OnBoardingFragment : Fragment() {

    //viewbinding variable

    private lateinit var binding: FragmentOnBoardingBinding

    //adapter for view pager onboarding
    private lateinit var adapterPager: ViewPagerOnBoarding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnBoardingBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterPager = ViewPagerOnBoarding(requireActivity().supportFragmentManager,lifecycle)
        setUpPagerAdapter()

        goToLogin()

    }

    private fun setUpPagerAdapter(){
        binding.pager.adapter = adapterPager
    }


    //get Preferences go to login or onBoarding

    //here fun returns boolean which shows did user page all pages onBoarding or not
    private fun isOnBoarding():Boolean{

        val pref = activity?.getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
        val isGoLogin = pref?.getBoolean("IsGoToLogin",false)
        return isGoLogin ?: return false
    }

    private fun goToLogin(){

        if (isOnBoarding()){

            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }
    }




}