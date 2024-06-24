package com.example.tickets.presentation.viewholders

import android.content.Context
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.tickets.R
import com.example.tickets.databinding.TicketItemBinding
import com.example.tickets.domain.models.Badge
import com.example.tickets.domain.models.TicketItem
import com.example.util.dpToPixels

class TicketsViewHolder(private val parentBinding: TicketItemBinding) :
    RecyclerView.ViewHolder(parentBinding.root) {
    private val radiusIconTrackPx = dpToPixels(RADIUS_DP, parentBinding.root.context)

    fun bind(model: TicketItem) = with(parentBinding) {
        badge.isVisible = model.badge == Badge.VALUE
        ticketPrice.text = model.price
        airportArrival.text = model.arrivalAirport
        airportDeparture.text = model.departureAirport
        timeInterval.text = "${model.departureTime} — ${model.arrivalTime}"
        timeRange.text = getTimeRange(model, itemView.context)
        Glide
            .with(itemView.context)
            .load(R.drawable.ic_circle_red)
            .transform(CenterCrop(), RoundedCorners(radiusIconTrackPx))
            .into(icCircle)
    }
}

private fun getTimeRange(model: TicketItem, context: Context): String {
    return if (model.hasTransfer) {
        "${model.duration}${context.getString(R.string.end_duration)}/${context.getString(R.string.without_transfers)}"
    } else {
        "${model.duration}${context.getString(R.string.end_duration)}"
    }
}

private const val RADIUS_DP = 16.0f