package com.example.tickets.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tickets.databinding.OfferItemBinding
import com.example.tickets.domain.models.OfferData
import com.example.tickets.presentation.viewholders.OfferViewHolder

class OfferAdapter : ListAdapter<OfferData, OfferViewHolder>(OfferDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        val binding = OfferItemBinding.inflate(layoutInspector, parent, false)
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

private class OfferDiffUtil : DiffUtil.ItemCallback<OfferData>() {
    override fun areItemsTheSame(oldItem: OfferData, newItem: OfferData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: OfferData, newItem: OfferData): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.town == newItem.town &&
                oldItem.title == newItem.title &&
                oldItem.price == newItem.price
    }
}