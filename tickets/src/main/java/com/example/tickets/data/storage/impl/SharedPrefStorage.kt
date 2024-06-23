package com.example.tickets.data.storage.impl

import android.content.SharedPreferences
import com.example.tickets.R
import com.example.tickets.data.models.RecommendationsItemDto
import com.example.tickets.data.storage.api.DataStorage

class SharedPrefStorage(private val sharedPref: SharedPreferences) : DataStorage {
    override fun setDepartureTown(town: String) {
        sharedPref.edit().putString(TOWN, town).apply()
    }

    override fun getDepartureTown(): String {
        return sharedPref.getString(TOWN, EMPTY_STR) ?: EMPTY_STR
    }

    override fun getRecommendationsList(): List<RecommendationsItemDto> {
        return listOf(
            RecommendationsItemDto(
                icon = R.drawable.ic_istanbul,
                destinationMane = R.string.istanbul,
                label = R.string.popular_destination
            ),
            RecommendationsItemDto(
                icon = R.drawable.ic_sochi,
                destinationMane = R.string.sochi,
                label = R.string.popular_destination
            ),
            RecommendationsItemDto(
                icon = R.drawable.ic_phuket,
                destinationMane = R.string.phuket,
                label = R.string.popular_destination
            ),

            )
    }
}

private const val EMPTY_STR = ""
private const val TOWN = "town"