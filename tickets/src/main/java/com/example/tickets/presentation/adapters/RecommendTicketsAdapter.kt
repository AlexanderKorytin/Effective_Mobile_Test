package com.example.tickets.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tickets.databinding.RecommendTicketItemBinding
import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.presentation.viewholders.RecommendTicketViewHolder

class RecommendTicketsAdapter :
    ListAdapter<RecommendTicket, RecommendTicketViewHolder>(RecommendTicketsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendTicketViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        val binding = RecommendTicketItemBinding.inflate(layoutInspector, parent, false)
        return RecommendTicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendTicketViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}


private class RecommendTicketsDiffUtil : DiffUtil.ItemCallback<RecommendTicket>() {
    override fun areItemsTheSame(oldItem: RecommendTicket, newItem: RecommendTicket): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecommendTicket, newItem: RecommendTicket): Boolean {
        return oldItem.price == newItem.price &&
                oldItem.title == newItem.title &&
                oldItem.title == newItem.title
    }

}