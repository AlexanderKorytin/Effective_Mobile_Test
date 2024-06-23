package com.example.tickets.domain.api.interacrors

import com.example.tickets.domain.models.OfferData
import com.example.tickets.domain.models.RecommendItem
import com.example.tickets.domain.models.SearchResultData
import com.example.tickets.domain.models.Town
import kotlinx.coroutines.flow.Flow

interface MainInteractor {
    suspend fun getMainData(): Flow<SearchResultData<List<OfferData>>>
    fun getCurrentTown(): Town
    fun setDepartureTown(town: Town)
    fun getRecommendationsList(): List<RecommendItem>
}