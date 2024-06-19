package com.example.data.models.tickets

import com.example.data.models.Price
import com.google.gson.annotations.SerializedName

data class Luggage(
    @SerializedName("has_luggage:")
    val hasLuggage: Boolean,
    val price: Price
)