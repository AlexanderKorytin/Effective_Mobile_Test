package com.example.tickets.presentation.models.search

import kotlin.math.E

data class SearchScreenState(
    val data: SearchScreenStateResponse = SearchScreenStateResponse.IsLoading,
    val departureTown: String = EMPTY_NAME,
    val arrivalTown: String = EMPTY_NAME,
    val thereDate: String = EMPTY_NAME,
    val thereDayName: String = EMPTY_NAME,
    val backDate: String = EMPTY_NAME
)

private const val EMPTY_NAME = ""