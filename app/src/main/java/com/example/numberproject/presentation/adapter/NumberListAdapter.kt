package com.example.numberproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.numberproject.data.local.entity.Number
import com.example.numberproject.databinding.NumberListItemBinding

class NumberListAdapter()
    : ListAdapter<Number, NumberListAdapter.NumberViewHolder>(DiffCallback) {

    class NumberViewHolder(private var binding: NumberListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind (num: Number){
                binding.apply {
                    number.text = num.number.toString()
                    numberFact.text = num.fact
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        return NumberViewHolder(
            NumberListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Number>() {
            override fun areItemsTheSame(oldItem: Number, newItem: Number): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Number, newItem: Number): Boolean {
                return oldItem.number == newItem.number
            }
        }
    }
}