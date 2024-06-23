package com.example.tickets.presentation.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.tickets.R
import com.example.tickets.databinding.RecommendTicketItemBinding
import com.example.tickets.domain.models.RecommendTicket
import com.example.util.dpToPixels

class RecommendTicketViewHolder(private val parentBinding: RecommendTicketItemBinding) :
    RecyclerView.ViewHolder(parentBinding.root) {
    private val radiusIconTrackPx = dpToPixels(RADIUS_DP, parentBinding.root.context)

    fun bind(model: RecommendTicket) = with(parentBinding) {
        companyName.text = model.title
        timeRange.text = model.timeRange
        price.text = model.price
        Glide
            .with(root.context)
            .load(getIcon(model.iconId))
            .transform(CenterCrop(), RoundedCorners(radiusIconTrackPx))
            .into(icCircle)
    }
}

private fun getIcon(iconId: Int): Int {
    return when (iconId) {
        1 -> {
            R.drawable.ic_circle_red
        }

        2 -> {
            R.drawable.ic_circle_blue
        }

        3 -> {
            R.drawable.ic_circle_white
        }

        else -> {
            R.drawable.ic_circle_red
        }
    }
}

private const val RADIUS_DP = 12.0f