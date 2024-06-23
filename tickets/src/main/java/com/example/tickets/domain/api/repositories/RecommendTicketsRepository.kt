package com.example.tickets.domain.api.repositories

import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.domain.models.SearchResultData
import kotlinx.coroutines.flow.Flow

interface RecommendTicketsRepository {
    suspend fun getRecommendTickets(): Flow<SearchResultData<List<RecommendTicket>>>
}