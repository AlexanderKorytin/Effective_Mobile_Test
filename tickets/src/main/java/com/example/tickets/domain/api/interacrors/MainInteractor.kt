package com.example.tickets.domain.api.interacrors

import com.example.tickets.domain.models.MainData
import com.example.tickets.domain.models.SearchResultData
import kotlinx.coroutines.flow.Flow

interface MainInteractor {
    suspend fun getMainData(): Flow<SearchResultData<List<MainData>>>
    fun getCurrentTown(): String
}