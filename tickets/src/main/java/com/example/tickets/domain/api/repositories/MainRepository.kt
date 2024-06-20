package com.example.tickets.domain.api.repositories

import com.example.tickets.domain.models.MainData
import com.example.tickets.domain.models.SearchResultData
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getMainData(): Flow<SearchResultData<List<MainData>>>
}