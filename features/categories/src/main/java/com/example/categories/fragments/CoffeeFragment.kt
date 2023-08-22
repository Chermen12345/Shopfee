package com.example.categories.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categories.R
import com.example.categories.adapters.ProductAdapter
import com.example.categories.databinding.FragmentCoffeeBinding
import com.example.categories.viewmodel.CategoryViewModel
import com.example.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class CoffeeFragment : Fragment() {

    private lateinit var binding: FragmentCoffeeBinding

    private val apiViewModel: CategoryViewModel by viewModel()

    private val adapter: ProductAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCoffeeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiViewModel.getProductsByCategory("coffee")
        setUpRecyclerViewProduct()
        checkStateOfResponse()
    }

    private fun checkStateOfResponse(){
        lifecycleScope.launch {

            apiViewModel.stateOfResponseProductsByCategory.collectLatest {state->

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
        binding.rcCoffeeCat.adapter = adapter
        binding.rcCoffeeCat.layoutManager = LinearLayoutManager(context)
    }


    private fun hideProgressBar(){
        binding.prBar.visibility = View.GONE
    }

    private fun showProgressBar(){
        binding.prBar.visibility = View.VISIBLE
    }

    private fun message(message: String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }



}