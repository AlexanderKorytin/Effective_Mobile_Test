package com.example.tickets.domain.api

import com.example.tickets.domain.models.MainData
import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.domain.models.SearchResultData
import com.example.tickets.domain.models.TicketItem
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getMainData(): Flow<SearchResultData<List<MainData>>>

    suspend fun getRecommendTickets(): Flow<SearchResultData<List<RecommendTicket>>>

    suspend fun getAllTickets(): Flow<SearchResultData<List<TicketItem>>>
}