package com.example.shopfee.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.shopfee.presentation.adapters.ProductAdapter
import com.example.example.databinding.FragmentAllProductsBinding
import com.example.shopfee.presentation.viewmodel.CategoryViewModel
import com.example.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class AllProductsFragment : Fragment() {

    private lateinit var binding: FragmentAllProductsBinding

    private val apiViewModel: CategoryViewModel by viewModel()

    val adapter: ProductAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAllProductsBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiViewModel.getAllCoffee()
        checkStateOfResponse()

        setUpRecyclerProducts()


    }





    private fun checkStateOfResponse(){
        lifecycleScope.launch {

            apiViewModel.stateOfResponseAllCoffee.collectLatest {state->

                when(state){

                    is Resource.Success->{

                        hideProgressBar()
                        adapter.differ.submitList(state.data)
                    }

                    is Resource.Loading->{showProgressBar()}

                    is Resource.Error->{
                        hideProgressBar()
                        message("error")}


                }


            }

        }

    }

    private fun setUpRecyclerProducts(){

        binding.rcAllProductscat.adapter = adapter
        binding.rcAllProductscat.layoutManager = LinearLayoutManager(context)
    }

    private fun showProgressBar(){

        binding.prbar.visibility = View.VISIBLE
    }


    private fun hideProgressBar(){

        binding.prbar.visibility = View.GONE
    }


    private fun message(message: String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }




}