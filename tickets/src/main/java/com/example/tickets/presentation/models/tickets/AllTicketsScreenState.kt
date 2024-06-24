package com.example.tickets.presentation.models.tickets

data class AllTicketsScreenState(
    val data: AllTicketsScreenStateResponse = AllTicketsScreenStateResponse.IsLoading,
    val ticketInfo: TicketInfo = TicketInfo(),
)
