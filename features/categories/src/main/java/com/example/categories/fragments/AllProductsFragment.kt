package com.example.categories.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categories.R
import com.example.categories.adapters.ProductAdapter
import com.example.categories.databinding.FragmentAllProductsBinding
import com.example.categories.viewmodel.CategoryViewModel
import com.example.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin


class AllProductsFragment : Fragment() {

    private lateinit var binding: FragmentAllProductsBinding

    private val apiViewModel: CategoryViewModel by viewModel()

    private val adapter: ProductAdapter by inject()

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