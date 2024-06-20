package com.example.tickets.di

import com.example.tickets.data.storage.api.DataStorage
import com.example.tickets.data.storage.impl.SharedPrefStorage
import org.koin.dsl.module

val ticketsDataModule = module {
    single<DataStorage> {
        SharedPrefStorage(sharedPref = get())
    }
}