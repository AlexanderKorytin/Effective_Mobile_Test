package com.example.tickets.domain.impl

import com.example.tickets.domain.api.interacrors.MainInteractor
import com.example.tickets.domain.api.repositories.MainRepository
import com.example.tickets.domain.models.OfferData
import com.example.tickets.domain.models.RecommendItem
import com.example.tickets.domain.models.SearchResultData
import com.example.tickets.domain.models.Town
import kotlinx.coroutines.flow.Flow

class MainInteractorImpl(private val repository: MainRepository) : MainInteractor {
    override suspend fun getMainData(): Flow<SearchResultData<List<OfferData>>> {
        return repository.getMainData()
    }

    override fun getCurrentTown(): Town {
        return repository.getDepartureTown()
    }

    override fun setDepartureTown(town: Town) {
        repository.setDepartureTown(town)
    }

    override fun getRecommendationsList(): List<RecommendItem> {
        return repository.getRecommendationsList()
    }
}