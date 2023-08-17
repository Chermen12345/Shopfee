package com.example.shopfee.presentation.adapters


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shopfee.presentation.fragments.PageFragment1
import com.example.shopfee.presentation.fragments.PageFragment2
import com.example.shopfee.presentation.fragments.PageFragment3

class ViewPagerOnBoarding(fragmentManager: FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->(PageFragment1())
            1->(PageFragment2())
            else -> {PageFragment3()}
        }

    }
}