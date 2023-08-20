package com.example.shopfee.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentHomeBinding
import com.example.shopfee.presentation.adapters.ViewPagerCategoryAdapter
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var pagerAdapter: ViewPagerCategoryAdapter

    private val listOfTabs = arrayListOf<String>("All","Coffee","Non-Coffee")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPager()
    }


    private fun setUpPager(){
        pagerAdapter = ViewPagerCategoryAdapter(requireActivity().supportFragmentManager,lifecycle)

        binding.pagercategory.adapter = pagerAdapter
        TabLayoutMediator(binding.tabhome,binding.pagercategory){
            current_tab,pos->
            run { current_tab.text = listOfTabs[pos] }
        }.attach()
    }
}