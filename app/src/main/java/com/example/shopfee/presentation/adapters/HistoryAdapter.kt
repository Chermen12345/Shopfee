package com.example.shopfee.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Order
import com.example.shopfee.databinding.OneInHistoryBinding

class HistoryAdapter(): RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {
    inner class HistoryHolder(val binding: OneInHistoryBinding): RecyclerView.ViewHolder(binding.root) {

    }



    val utils = object : DiffUtil.ItemCallback<Order>(){
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.coffee == newItem.coffee
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
           return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, utils)






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        return HistoryHolder(OneInHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        Glide.with(holder.itemView).load("http://10.0.2.2:8080${differ.currentList[position].coffee!!.image}")
            .into(holder.binding.imghistory)

        holder.binding.apply {

            tvtitlehistory.text = differ.currentList[position].coffee!!.title
            tvpricehistory.text = "${differ.currentList[position].coffee!!.price}$"
            tvdeschistory.text = "${differ.currentList[position].variant}, ${differ.currentList[position].size}, " +
                    "${sugarNormalLess(differ.currentList[position].sugar)}, ${iceNormalLess(differ.currentList[position].ice)}"
        }
    }
    private fun sugarNormalLess(normalAmountSugar: Boolean): String{
        if (normalAmountSugar){
            return "normal sugar"
        }else{
            return "less sugar"
        }
    }
    private fun iceNormalLess(normalAmountIce: Boolean): String{
        if (normalAmountIce){
            return "normal Ice"
        }else{
            return "less Ice"
        }

    }




}