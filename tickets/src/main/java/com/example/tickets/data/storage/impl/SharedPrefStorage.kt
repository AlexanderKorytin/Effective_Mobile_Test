package com.example.tickets.data.storage.impl

import android.content.SharedPreferences
import com.example.tickets.data.storage.api.DataStorage

class SharedPrefStorage(private val sharedPref: SharedPreferences) : DataStorage {
    override fun setDepartureTown(town: String) {
        sharedPref.edit().putString(town, TOWN).apply()
    }

    override fun getDepartureTown(): String {
        return sharedPref.getString(TOWN, EMPTY_STR) ?: EMPTY_STR
    }
}

private const val EMPTY_STR = ""
private const val TOWN = "town"