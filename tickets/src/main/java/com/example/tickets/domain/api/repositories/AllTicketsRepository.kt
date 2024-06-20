package com.example.tickets.domain.api.repositories

import com.example.tickets.domain.models.SearchResultData
import com.example.tickets.domain.models.TicketItem
import kotlinx.coroutines.flow.Flow

interface AllTicketsRepository {
    suspend fun getAllTickets(): Flow<SearchResultData<List<TicketItem>>>
}