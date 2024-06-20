package com.example.tickets.domain.api.interacrors

import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.domain.models.SearchResultData
import kotlinx.coroutines.flow.Flow

interface CheckedTicketsInteractor {
    suspend fun getRecommendTickets(): Flow<SearchResultData<List<RecommendTicket>>>
}