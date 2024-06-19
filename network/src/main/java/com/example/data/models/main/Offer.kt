package com.example.data.models.main

import com.example.data.models.Price

data class Offer(
    val id: Int,
    val price: Price,
    val title: String,
    val town: String
)