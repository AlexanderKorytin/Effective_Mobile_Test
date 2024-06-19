package com.example.data.client.api

import com.example.data.models.main.MainDataResponseDto
import com.example.data.models.search.RecommendTicketsDto
import com.example.data.models.tickets.TicketsDto

interface NetworkClient {
    suspend fun getMainData(): Result<MainDataResponseDto>

    suspend fun getRecommendTickets(): Result<RecommendTicketsDto>

    suspend fun getAllTickets(): Result<TicketsDto>
}