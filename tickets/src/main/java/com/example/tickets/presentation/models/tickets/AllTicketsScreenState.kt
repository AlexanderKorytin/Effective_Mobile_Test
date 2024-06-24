package com.example.tickets.presentation.models.tickets

import com.example.tickets.presentation.models.search.SearchScreenStateResponse

data class AllTicketsScreenState(
    val data: AllTicketsScreenStateResponse = AllTicketsScreenStateResponse.IsLoading,
    val ticketInfo: TicketInfo = TicketInfo(),
)
