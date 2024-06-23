package com.example.tickets.data.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class RecommendationsItemDto(
    @DrawableRes val icon: Int,
    @StringRes val destinationMane: Int,
    @StringRes val label: Int
)
