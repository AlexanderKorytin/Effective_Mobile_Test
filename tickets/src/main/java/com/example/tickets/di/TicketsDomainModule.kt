package com.example.tickets.di

import com.example.tickets.data.repository.impl.AllTicketsRepositoryImpl
import com.example.tickets.data.repository.impl.RecommendTicketsRepositoryImpl
import com.example.tickets.data.repository.impl.MainRepositoryImpl
import com.example.tickets.domain.api.interacrors.AllTicketsInteractor
import com.example.tickets.domain.api.interacrors.RecommendTicketsInteractor
import com.example.tickets.domain.api.interacrors.MainInteractor
import com.example.tickets.domain.api.repositories.AllTicketsRepository
import com.example.tickets.domain.api.repositories.RecommendTicketsRepository
import com.example.tickets.domain.api.repositories.MainRepository
import com.example.tickets.domain.impl.AllTicketsInteractorImpl
import com.example.tickets.domain.impl.RecommendTicketsInteractorImpl
import com.example.tickets.domain.impl.MainInteractorImpl
import org.koin.dsl.module

val ticketsDomainModule = module {
    single<MainRepository> {
        MainRepositoryImpl(client = get(), storage = get())
    }
    single<RecommendTicketsRepository> {
        RecommendTicketsRepositoryImpl(client = get())
    }
    single<AllTicketsRepository> {
        AllTicketsRepositoryImpl(client = get())
    }

    factory<MainInteractor> {
        MainInteractorImpl(repository = get())
    }
    factory<RecommendTicketsInteractor> {
        RecommendTicketsInteractorImpl(repository = get())
    }
    factory<AllTicketsInteractor> {
        AllTicketsInteractorImpl(repository = get())
    }
}
