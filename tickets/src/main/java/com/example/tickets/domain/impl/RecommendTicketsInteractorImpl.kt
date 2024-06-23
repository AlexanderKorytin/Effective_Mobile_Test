package com.example.tickets.domain.impl

import com.example.tickets.domain.api.interacrors.RecommendTicketsInteractor
import com.example.tickets.domain.api.repositories.RecommendTicketsRepository
import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.domain.models.SearchResultData
import kotlinx.coroutines.flow.Flow

class RecommendTicketsInteractorImpl(private val repository: RecommendTicketsRepository) :
    RecommendTicketsInteractor {
    override suspend fun getRecommendTickets(): Flow<SearchResultData<List<RecommendTicket>>> {
        return repository.getRecommendTickets()
    }
}