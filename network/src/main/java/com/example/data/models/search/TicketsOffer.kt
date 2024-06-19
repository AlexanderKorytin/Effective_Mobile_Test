package com.example.data.models.search

import com.example.data.models.Price
import com.google.gson.annotations.SerializedName

data class TicketsOffer(
    val id: Int,
    val price: Price,
    @SerializedName("time_range")
    val timeRange: List<String>,
    val title: String
)