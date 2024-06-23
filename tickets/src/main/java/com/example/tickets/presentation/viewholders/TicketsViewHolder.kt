package com.example.tickets.presentation.viewholders

import android.content.Context
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.R
import com.example.tickets.databinding.TicketItemBinding
import com.example.tickets.domain.models.Badge
import com.example.tickets.domain.models.TicketItem

class TicketsViewHolder(private val parentBinding: TicketItemBinding) :
    RecyclerView.ViewHolder(parentBinding.root) {
    fun bind(model: TicketItem) {
        parentBinding.badge.isVisible = model.badge == Badge.VALUE
        parentBinding.ticketPrice.text = model.price
        parentBinding.airportArrival.text = model.arrivalAirport
        parentBinding.airportDeparture.text = model.departureAirport
        parentBinding.timeInterval.text = "${model.departureTime} — ${model.arrivalTime}"
        parentBinding.timeRange.text = getTimeRange(model, itemView.context)
    }
}

private fun getTimeRange(model: TicketItem, context: Context): String {
    return if (model.hasTransfer) {
        "${model.duration}/${context.getString(R.string.without_transfers)}"
    } else {
        "${model.duration}"
    }
}