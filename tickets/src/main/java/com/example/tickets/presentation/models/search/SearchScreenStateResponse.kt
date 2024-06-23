package com.example.tickets.presentation.models.search

import androidx.annotation.StringRes
import com.example.tickets.domain.models.OfferData
import com.example.tickets.domain.models.RecommendTicket

sealed interface SearchScreenStateResponse {
    data object IsLoading : SearchScreenStateResponse
    data class Content(val tickets: List<RecommendTicket>) : SearchScreenStateResponse
    data class Error(@StringRes val message: Int) : SearchScreenStateResponse
    data class NoInternet(@StringRes val message: Int) :SearchScreenStateResponse
}
