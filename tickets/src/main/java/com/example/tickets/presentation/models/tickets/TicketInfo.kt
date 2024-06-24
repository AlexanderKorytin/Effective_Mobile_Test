package com.example.tickets.presentation.models.tickets

import java.io.Serializable

data class TicketInfo(
    val departureTown: String = EMPTY,
    val arrivalTown: String = EMPTY,
    val ticketSettings: String = EMPTY,
    val dateDeparture: String = EMPTY
) : Serializable

private const val EMPTY = ""