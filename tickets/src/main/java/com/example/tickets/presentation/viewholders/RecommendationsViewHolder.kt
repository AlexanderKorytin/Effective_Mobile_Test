package com.example.tickets.presentation.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.tickets.databinding.RecommendationItemBinding
import com.example.tickets.domain.models.RecommendItem
import com.example.util.dpToPixels

class RecommendationsViewHolder(private val parentBinding: RecommendationItemBinding) :
    RecyclerView.ViewHolder(parentBinding.root) {
    private val radiusIconTrackPx = dpToPixels(RADIUS_DP, parentBinding.root.context)

    fun bind(model: RecommendItem) = with(parentBinding) {
        townNameRecommendation.text = itemView.context.getString(model.destinationMane)
        labelRecommendation.text = itemView.context.getString(model.label)
        Glide
            .with(root.context)
            .load(model.icon)
            .transform(CenterCrop(), RoundedCorners(radiusIconTrackPx))
            .into(icRecommendation)
    }
}

private const val RADIUS_DP = 16.0f