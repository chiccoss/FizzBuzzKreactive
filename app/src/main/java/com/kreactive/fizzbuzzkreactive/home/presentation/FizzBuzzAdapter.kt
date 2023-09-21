package com.kreactive.fizzbuzzkreactive.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kreactive.fizzbuzzkreactive.databinding.ItemFizzBuzzBinding

class FizzBuzzAdapter : ListAdapter<String, FizzBuzzAdapter.ItemFizzBuzzViewHolder>(DiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemFizzBuzzViewHolder(
        ItemFizzBuzzBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ItemFizzBuzzViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemFizzBuzzViewHolder(private val binding: ItemFizzBuzzBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            binding.itemFizzBuzzTv.text = data
        }
    }


    class DiffItemCallback : DiffUtil.ItemCallback<String>() {

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }
}