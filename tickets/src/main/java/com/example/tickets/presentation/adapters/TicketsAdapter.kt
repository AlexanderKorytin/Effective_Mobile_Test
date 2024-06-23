package com.example.tickets.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tickets.databinding.TicketItemBinding
import com.example.tickets.domain.models.TicketItem
import com.example.tickets.presentation.viewholders.TicketsViewHolder

class TicketsAdapter : ListAdapter<TicketItem, TicketsViewHolder>(TicketsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        val binding = TicketItemBinding.inflate(layoutInspector, parent, false)
        return TicketsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

private class TicketsDiffUtil : DiffUtil.ItemCallback<TicketItem>() {
    override fun areItemsTheSame(oldItem: TicketItem, newItem: TicketItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TicketItem, newItem: TicketItem): Boolean {
        return oldItem.badge == newItem.badge &&
                oldItem.hasTransfer == newItem.hasTransfer &&
                oldItem.price == newItem.price &&
                oldItem.departureTime == newItem.departureTime &&
                oldItem.arrivalTime == newItem.arrivalTime &&
                oldItem.arrivalAirport == newItem.arrivalAirport &&
                oldItem.departureAirport == newItem.departureAirport &&
                oldItem.duration == newItem.duration
    }
}