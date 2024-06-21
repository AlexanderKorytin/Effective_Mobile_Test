package com.example.tickets.presentation.models.main

import androidx.annotation.StringRes
import com.example.tickets.domain.models.OfferData

sealed interface MainScreenStateResponse {
    data object IsLoading : MainScreenStateResponse
    data class Content(val offers: List<OfferData>) : MainScreenStateResponse
    data class Error(@StringRes val message: Int) : MainScreenStateResponse
    data class NoInternet(@StringRes val message: Int) : MainScreenStateResponse
}