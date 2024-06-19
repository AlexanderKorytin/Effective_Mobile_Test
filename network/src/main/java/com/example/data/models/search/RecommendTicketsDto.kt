package com.example.data.models.search

import com.google.gson.annotations.SerializedName

data class RecommendTicketsDto(
    @SerializedName("tickets_offers")
    val tickets: List<TicketsOffer>
)