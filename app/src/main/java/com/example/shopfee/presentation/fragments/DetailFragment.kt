package com.example.shopfee.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.domain.model.Coffee
import com.example.domain.model.Order
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentDetailBinding
import com.example.shopfee.presentation.fragments.NonCoffeeFragment.Companion.DETAIL_ARGUMENT
import com.example.shopfee.presentation.viewmodel.FirebaseViewModel
import com.example.utils.UiState
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private var coffee: Coffee ?= null

    private val fireViewModel: FirebaseViewModel by viewModel()

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


        addOrder()
        lifecycleScope.launch {
            checkUiStateWhenAddOrder()
        }

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


    private fun addOrder(){


        binding.apply {
            btaddorder.setOnClickListener {

                val order = Order(coffee,coffeeVariant(),coffeeSize(),amountOfSugar(),amountOfIce())
                fireViewModel.addOrder(order)

            }
        }
    }


    private suspend fun checkUiStateWhenAddOrder(){

        fireViewModel.stateOfAddingOrder.collectLatest {uistate->
            when(uistate){

                is UiState.Success->{message(uistate.message!!)}
                is UiState.Loading->{}
                is UiState.Error->{message(uistate.message!!)}
            }
        }
    }



    private fun coffeeVariant(): String{
        if (binding.variantradio1.isChecked){
            return "ice"
        }else{
            return "hot"
        }

    }

    private fun coffeeSize(): String{
        if (binding.sizeradio1.isChecked){
            return "Regular"
        }else if (binding.sizeradio2.isChecked){
            return "Medium"
        }else{
            return "Large"
        }
    }


    private fun amountOfSugar(): Boolean{
        return binding.sugarradio1.isChecked
    }

    private fun amountOfIce(): Boolean{
        return binding.iceradio1.isChecked
    }


    private fun goBack(){
        binding.btback.setOnClickListener {

            findNavController().navigate(R.id.action_detailFragment_to_homefr)
        }
    }


    private fun message(message: String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

}