package com.example.tickets.presentation.models.main

import androidx.annotation.StringRes
import com.example.tickets.domain.models.OfferData
import com.example.tickets.domain.models.Town

sealed interface MainScreenState {
    data class IsLoading(val town: Town): MainScreenState
    data class Content(val offers: List<OfferData>, val town: Town) : MainScreenState
    data class Error(@StringRes val message: Int, val town: Town) : MainScreenState
    data class NoInternet(@StringRes val message: Int, val town: Town) : MainScreenState
}