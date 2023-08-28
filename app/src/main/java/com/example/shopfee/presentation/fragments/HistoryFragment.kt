package com.example.shopfee.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.Coffee

import com.example.domain.model.Order
import com.example.shopfee.R
import com.example.shopfee.databinding.FragmentHistoryBinding
import com.example.shopfee.presentation.adapters.ClickEvents
import com.example.shopfee.presentation.adapters.HistoryAdapter
import com.example.shopfee.presentation.adapters.ProductAdapter
import com.example.utils.Consts.AUTH
import com.example.utils.Consts.FIRESTORE



class HistoryFragment : Fragment(){

    private lateinit var binding: FragmentHistoryBinding

    private lateinit var adapter: HistoryAdapter
    private val listOfOrders = arrayListOf<Order>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHistoryBinding.inflate(inflater)
        adapter = HistoryAdapter()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getOrders()


    }


    private fun getOrders(){
        FIRESTORE.collection("users").document(AUTH.currentUser!!.uid)
            .collection("addedOrders").get().addOnSuccessListener {
                if (!it.isEmpty){
                    for (data in it.documents){
                        val order = data.toObject(Order::class.java)
                        order?.let { listOfOrders.add(it) }
                        Log.d("tagg", order!!.coffee!!.title)
                    }
                    adapter.differ.submitList(listOfOrders)
                    binding.rchistory.adapter = adapter
                    binding.rchistory.layoutManager = LinearLayoutManager(context)
                }
            }

    }


}