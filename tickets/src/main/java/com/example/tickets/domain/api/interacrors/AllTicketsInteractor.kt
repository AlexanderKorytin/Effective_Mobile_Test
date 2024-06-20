package com.example.tickets.domain.api.interacrors

import com.example.tickets.domain.models.SearchResultData
import com.example.tickets.domain.models.TicketItem
import kotlinx.coroutines.flow.Flow

interface AllTicketsInteractor {
    suspend fun getAllTickets(): Flow<SearchResultData<List<TicketItem>>>
}