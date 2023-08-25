package com.example.shopfee.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shopfee.presentation.fragments.AllProductsFragment
import com.example.shopfee.presentation.fragments.CoffeeFragment
import com.example.shopfee.presentation.fragments.NonCoffeeFragment


class ViewPagerCategoryAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0-> return AllProductsFragment()
            1-> return CoffeeFragment()
        }
        return NonCoffeeFragment()
    }
}