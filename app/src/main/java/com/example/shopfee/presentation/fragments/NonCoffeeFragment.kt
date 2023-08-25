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
import com.example.categories.databinding.FragmentNonCoffeeBinding
import com.example.shopfee.presentation.viewmodel.CategoryViewModel
import com.example.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class NonCoffeeFragment : Fragment() {

    private lateinit var binding: FragmentNonCoffeeBinding

    private val apiViewModel: CategoryViewModel by viewModel()

    private val adapter: ProductAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNonCoffeeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getNonCoffeeProducts()
        setUpRecyclerViewProduct()
        checkStateOfResponse()
    }


    private fun getNonCoffeeProducts(){
        apiViewModel.getProductsByCategoryNonCoffee("noncoffee")
    }

    private fun checkStateOfResponse(){
        lifecycleScope.launch {

            apiViewModel.stateOfResponseProductsByCategoryNonCoffee.collectLatest {state->

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

    private fun setUpRecyclerViewProduct(){
        binding.rcNoncoffeecat.adapter = adapter
        binding.rcNoncoffeecat.layoutManager = LinearLayoutManager(context)
    }


    private fun hideProgressBar(){
        binding.prBar2.visibility = View.GONE
    }

    private fun showProgressBar(){
        binding.prBar2.visibility = View.VISIBLE
    }

    private fun message(message: String){
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }


}