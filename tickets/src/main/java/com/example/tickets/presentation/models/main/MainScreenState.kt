package com.example.tickets.presentation.models.main

import com.example.tickets.domain.models.RecommendItem
import com.example.tickets.domain.models.Town

data class MainScreenState(
    val data: MainScreenStateResponse = MainScreenStateResponse.IsLoading,
    val departureTown: Town = Town(EMPTY_NAME),
    val listRecommend: List<RecommendItem> = emptyList()
)

private const val EMPTY_NAME = ""