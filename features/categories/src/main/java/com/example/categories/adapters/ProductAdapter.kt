package com.example.categories.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.categories.databinding.OneProductBinding
import com.example.domain.model.Coffee

class ProductAdapter(): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    inner class ProductHolder(val binding: OneProductBinding): RecyclerView.ViewHolder(binding.root)

    val util = object: DiffUtil.ItemCallback<Coffee>(){
        override fun areItemsTheSame(oldItem: Coffee, newItem: Coffee): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Coffee, newItem: Coffee): Boolean {
            return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this, util)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(OneProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        Glide.with(holder.itemView).load("http://10.0.2.2:8080${differ.currentList[position].image}")
            .into(holder.binding.imgProduct)
        holder.binding.apply {

            tvdesc.text = differ.currentList[position].description
            tvtitle.text = differ.currentList[position].title
            tvprice.text = "${differ.currentList[position].price}.00$"
            tvrating.text = differ.currentList[position].rating.toString()
        }
    }


}