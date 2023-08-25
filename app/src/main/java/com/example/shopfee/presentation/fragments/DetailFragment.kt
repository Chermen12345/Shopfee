package com.example.shopfee.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentDetailBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBottomSheetDialog()
    }
    private fun setUpBottomSheetDialog(){

        BottomSheetBehavior.from(binding.btSheet).peekHeight = 400
    }
}