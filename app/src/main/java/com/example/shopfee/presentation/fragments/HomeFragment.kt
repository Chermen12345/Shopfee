package com.example.shopfee.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.categories.adapters.ProductAdapter
import com.example.categories.fragments.AllProductsFragment
import com.example.domain.model.Coffee
import com.example.shopfee.R


import com.example.shopfee.databinding.FragmentHomeBinding
import com.example.shopfee.presentation.adapters.ViewPagerCategoryAdapter
import com.example.shopfee.presentation.viewmodel.AppViewModel
import com.example.utils.Resource
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding

    private lateinit var pagerAdapter: ViewPagerCategoryAdapter

    private val listOfTabs = arrayListOf<String>("All","Coffee","Non-Coffee")

    private val viewModel: AppViewModel by viewModel()

    private val adapter: ProductAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPager()

        openSearch()
        removeSearch()

        setUpRecyclerViewSearch()

        searchProduct()

        adapter.onItemClick = {
            findNavController().navigate(R.id.action_homefr_to_detailFragment)
        }

    }


    private fun setUpPager(){
        pagerAdapter = ViewPagerCategoryAdapter(requireActivity().supportFragmentManager,lifecycle)

        binding.pagercategory.adapter = pagerAdapter
        TabLayoutMediator(binding.tabhome,binding.pagercategory){
            current_tab,pos->
            run { current_tab.text = listOfTabs[pos] }
        }.attach()
    }



    private fun openSearch(){
        binding.edSearchHome.setOnClickListener {
            binding.notificationbt.visibility = View.GONE
            binding.imageView6.visibility = View.GONE
            binding.tabhome.visibility = View.GONE
            binding.pagercategory.visibility = View.GONE


            binding.searchbt.visibility = View.VISIBLE
            binding.rcSearch.visibility = View.VISIBLE
            binding.btremovesearch.visibility = View.VISIBLE
        }
    }


    private fun removeSearch(){
        binding.btremovesearch.setOnClickListener {
            lifecycleScope.launch {
                adapter.differ.submitList(emptyList())
                delay(500)
                binding.notificationbt.visibility = View.VISIBLE
                binding.imageView6.visibility = View.VISIBLE
                binding.tabhome.visibility = View.VISIBLE
                binding.pagercategory.visibility = View.VISIBLE

                binding.searchbt.visibility = View.GONE
                binding.rcSearch.visibility = View.GONE
                binding.btremovesearch.visibility = View.GONE
            }



        }
    }

    private fun searchProduct(){
        binding.apply {

            searchbt.setOnClickListener {
                val q = edSearchHome.text.toString()
                edSearchHome.text.clear()
                viewModel.searchProduct(q)
                checkStateOfResponse()
            }

        }
    }
    private fun checkStateOfResponse(){
        lifecycleScope.launch {

            viewModel.stateOfResponseSearch.collectLatest {state->

                when(state){

                    is Resource.Success->{
                        hideProgressBar()
                        if (state.data!!.isNotEmpty()){
                            adapter.differ.submitList(state.data)
                        }else{
                            message("sorry, but we couldnt find anything")
                        }

                    }

                    is Resource.Loading->{showProgressBar()}

                    is Resource.Error->{
                        hideProgressBar()
                        message("error")}


                }


            }

        }

    }

    private fun setUpRecyclerViewSearch(){
        binding.rcSearch.adapter = adapter
        binding.rcSearch.layoutManager = LinearLayoutManager(context)
    }

    private fun showProgressBar(){
        binding.prBar3.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.prBar3.visibility = View.GONE
    }


    private fun message(message: String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }


}