package com.example.tickets.presentation.models.tickets

import androidx.annotation.StringRes
import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.domain.models.TicketItem
import com.example.tickets.presentation.models.search.SearchScreenStateResponse

sealed interface AllTicketsScreenStateResponse {
    data object IsLoading : AllTicketsScreenStateResponse
    data class Content(val tickets: List<TicketItem>) : AllTicketsScreenStateResponse
    data class Error(@StringRes val message: Int) : AllTicketsScreenStateResponse
    data class NoInternet(@StringRes val message: Int) : AllTicketsScreenStateResponse
}