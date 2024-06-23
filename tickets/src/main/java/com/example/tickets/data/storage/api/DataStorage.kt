package com.example.tickets.data.storage.api

import com.example.tickets.data.models.RecommendationsItemDto

interface DataStorage {
    fun setDepartureTown(town: String)
    fun getDepartureTown(): String
    fun getRecommendationsList(): List<RecommendationsItemDto>
}