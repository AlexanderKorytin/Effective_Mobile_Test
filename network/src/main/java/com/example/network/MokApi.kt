package com.example.network

import com.example.data.models.main.MainDataResponseDto
import com.example.data.models.search.RecommendTicketsDto
import com.example.data.models.tickets.TicketsDto
import retrofit2.http.GET

private const val MAIN_END = "ad9a46ba-276c-4a81-88a6-c068e51cce3a"
private const val RECOMMEND_END = "38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a"
private const val ALL_TICKETS_END = "c0464573-5a13-45c9-89f8-717436748b69"

interface MokApi {
    @GET(MAIN_END)
    suspend fun getMainData(): MainDataResponseDto

    @GET(RECOMMEND_END)
    suspend fun getRecommendTickets(): RecommendTicketsDto

    @GET(ALL_TICKETS_END)
    suspend fun getAllTickets(): TicketsDto
}