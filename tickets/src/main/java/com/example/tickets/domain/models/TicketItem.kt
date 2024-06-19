package com.example.tickets.domain.models

data class TicketItem(
    val badge: Badge,
    val price: String,
    val departureDate: String,
    val departureAirport: String,
    val arrivalDate: String,
    val arrivalAirport: String,
    val duration: String,
    val hasTransfer: Boolean

)