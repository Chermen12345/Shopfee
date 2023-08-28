package com.example.shopfee.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.domain.model.Coffee
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentDetailBinding
import com.example.shopfee.presentation.fragments.NonCoffeeFragment.Companion.DETAIL_ARGUMENT
import com.google.android.material.bottomsheet.BottomSheetBehavior


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    var coffee: Coffee ?= null

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

        getArgument()
        putToUi()

        goBack()
    }
    private fun setUpBottomSheetDialog(){

        BottomSheetBehavior.from(binding.btSheet).peekHeight = 600
    }

    //here we get coffee data class with all detailes
    private fun getArgument(){
        val args = arguments

        args?.let {bundle ->
            coffee = bundle.getSerializable(DETAIL_ARGUMENT) as Coffee

        }

    }


    private fun putToUi(){
        coffee?.let {
            it.apply {
                Glide.with(this@DetailFragment).load("http://10.0.2.2:8080$image").into(binding.imgdetail)
                binding.tvdetailcat.text  = category
                binding.tvdetaildesc.text = description
                binding.tvdetailrating.text = "$rating"
                binding.tvdetailprice.text = "$price$"
                binding.tvdetailprice2.text = "$price$"
                binding.tvdetailtitle.text = "$title"

                if (category.equals("noncoffee")){
                    binding.radioGroup1.visibility = View.GONE
                    binding.radioGroup2.visibility = View.GONE
                    binding.radioGroup3.visibility = View.GONE
                    binding.radioGroup4.visibility = View.GONE
                    binding.textView10.visibility = View.GONE
                    binding.textView11.visibility = View.GONE
                    binding.textView12.visibility = View.GONE
                    binding.textView13.visibility = View.GONE
                    binding.textView14.visibility = View.GONE
                }
            }


        }

    }



    private fun goBack(){
        binding.btback.setOnClickListener {

            findNavController().navigate(R.id.action_detailFragment_to_homefr)
        }
    }

}