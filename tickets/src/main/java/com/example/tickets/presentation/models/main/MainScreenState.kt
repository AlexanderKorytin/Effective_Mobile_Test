package com.example.tickets.presentation.models.main

import com.example.tickets.domain.models.Town

data class MainScreeState(
    val data: MainScreenStateResponse = MainScreenStateResponse.IsLoading,
    val departureTown: Town = Town(EMPTY_NAME)
)

private const val EMPTY_NAME = ""