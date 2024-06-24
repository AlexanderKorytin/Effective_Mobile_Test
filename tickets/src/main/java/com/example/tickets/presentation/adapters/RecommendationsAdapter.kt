package com.example.tickets.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tickets.databinding.RecommendationItemBinding
import com.example.tickets.domain.models.RecommendItem
import com.example.tickets.presentation.viewholders.RecommendationsViewHolder

class RecommendationsAdapter(private val onClick: (RecommendItem) -> Unit) :
    ListAdapter<RecommendItem, RecommendationsViewHolder>(RecommendDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        val binding = RecommendationItemBinding.inflate(layoutInspector, parent, false)
        return RecommendationsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendationsViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener {
            onClick(currentList[holder.adapterPosition])
        }
    }
}

private class RecommendDiffUtil : DiffUtil.ItemCallback<RecommendItem>() {
    override fun areItemsTheSame(oldItem: RecommendItem, newItem: RecommendItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecommendItem, newItem: RecommendItem): Boolean {
        return oldItem.icon == newItem.icon &&
                oldItem.destinationMane == newItem.destinationMane &&
                oldItem.label == newItem.label
    }

}