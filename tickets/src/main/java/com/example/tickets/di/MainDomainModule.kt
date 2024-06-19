package com.example.tickets.di

import com.example.tickets.data.repository.impl.MainRepositoryImpl
import com.example.tickets.domain.api.MainRepository
import org.koin.dsl.module

val aminDomainModule = module {
    single<MainRepository> {
        MainRepositoryImpl(client = get())
    }
}
