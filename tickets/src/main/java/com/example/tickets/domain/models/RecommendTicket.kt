package com.example.tickets.domain.models

import com.example.data.models.Price

data class RecommendTicket(
    val iconId: Int = 0,
    val price: String,
    val timeRange: String,
    val title: String
)