package com.example.tickets.domain.impl

import com.example.tickets.domain.api.interacrors.AllTicketsInteractor
import com.example.tickets.domain.api.repositories.AllTicketsRepository
import com.example.tickets.domain.models.SearchResultData
import com.example.tickets.domain.models.TicketItem
import kotlinx.coroutines.flow.Flow

class AllTicketsInteractorImpl(private val repository: AllTicketsRepository) :
    AllTicketsInteractor {
    override suspend fun getAllTickets(): Flow<SearchResultData<List<TicketItem>>> {
        return repository.getAllTickets()
    }
}