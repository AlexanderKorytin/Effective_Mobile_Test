package com.example.tickets.data.storage.api

interface DataStorage {
    fun setDepartureTown(town: String)
    fun getDepartureTown(): String
}