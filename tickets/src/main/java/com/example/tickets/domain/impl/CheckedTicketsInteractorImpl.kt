package com.example.tickets.domain.impl

import com.example.tickets.domain.api.interacrors.CheckedTicketsInteractor
import com.example.tickets.domain.api.repositories.CheckedTicketsRepository
import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.domain.models.SearchResultData
import kotlinx.coroutines.flow.Flow

class CheckedTicketsInteractorImpl(private val repository: CheckedTicketsRepository) :
    CheckedTicketsInteractor {
    override suspend fun getRecommendTickets(): Flow<SearchResultData<List<RecommendTicket>>> {
        return repository.getRecommendTickets()
    }
}