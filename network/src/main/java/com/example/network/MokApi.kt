package com.example.network

import com.example.data.models.main.MainDataResponseDto
import com.example.data.models.search.RecommendTicketsDto
import com.example.data.models.tickets.TicketsDto
import retrofit2.http.GET

private const val MAIN_END = "214a1713-bac0-4853-907c-a1dfc3cd05fd"
private const val RECOMMEND_END = "7e55bf02-89ff-4847-9eb7-7d83ef884017"
private const val ALL_TICKETS_END = "c0464573-5a13-45c9-89f8-717436748b69"

interface MokApi {
    @GET(MAIN_END)
    suspend fun getMainData(): MainDataResponseDto

    @GET(RECOMMEND_END)
    suspend fun getRecommendTickets(): RecommendTicketsDto

    @GET(ALL_TICKETS_END)
    suspend fun getAllTickets(): TicketsDto
}