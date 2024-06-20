package com.example.tickets.domain.impl

import com.example.tickets.domain.api.interacrors.MainInteractor
import com.example.tickets.domain.api.repositories.MainRepository
import com.example.tickets.domain.models.MainData
import com.example.tickets.domain.models.SearchResultData
import kotlinx.coroutines.flow.Flow

class MainInteractorImpl(private val repository: MainRepository) : MainInteractor {
    override suspend fun getMainData(): Flow<SearchResultData<List<MainData>>> {
        return repository.getMainData()
    }
}