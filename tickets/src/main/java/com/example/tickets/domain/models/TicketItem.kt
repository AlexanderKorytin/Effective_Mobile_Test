package com.example.tickets.domain.models

data class TicketItem(
    val badge: Badge,
    val price: String,
    val departureTime: String,
    val departureAirport: String,
    val arrivalTime: String,
    val arrivalAirport: String,
    val duration: String,
    val hasTransfer: Boolean

)