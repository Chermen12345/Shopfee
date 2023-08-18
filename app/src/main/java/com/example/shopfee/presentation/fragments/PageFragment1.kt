package com.example.shopfee.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentPage1Binding
import com.example.utils.Functions


class PageFragment1 : Fragment() {

    private lateinit var binding: FragmentPage1Binding
    private val functions: Functions
        get() = OnBoardingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPage1Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changePageFor2()

    }


    private fun changePageFor2(){
        binding.btPage2.setOnClickListener {

            functions.onBoardingChangePageClick()


        }
    }
}