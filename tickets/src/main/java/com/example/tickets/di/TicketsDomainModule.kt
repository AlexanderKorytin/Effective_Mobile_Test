package com.example.tickets.di

import com.example.tickets.data.repository.impl.AllTicketsRepositoryImpl
import com.example.tickets.data.repository.impl.CheckedTicketsRepositoryImpl
import com.example.tickets.data.repository.impl.MainRepositoryImpl
import com.example.tickets.domain.api.interacrors.AllTicketsInteractor
import com.example.tickets.domain.api.interacrors.CheckedTicketsInteractor
import com.example.tickets.domain.api.interacrors.MainInteractor
import com.example.tickets.domain.api.repositories.AllTicketsRepository
import com.example.tickets.domain.api.repositories.CheckedTicketsRepository
import com.example.tickets.domain.api.repositories.MainRepository
import com.example.tickets.domain.impl.AllTicketsInteractorImpl
import com.example.tickets.domain.impl.CheckedTicketsInteractorImpl
import com.example.tickets.domain.impl.MainInteractorImpl
import org.koin.dsl.module

val aminDomainModule = module {
    single<MainRepository> {
        MainRepositoryImpl(client = get(), storage = get())
    }
    single<CheckedTicketsRepository> {
        CheckedTicketsRepositoryImpl(client = get())
    }
    single<AllTicketsRepository> {
        AllTicketsRepositoryImpl(client = get())
    }

    factory<MainInteractor> {
        MainInteractorImpl(repository = get())
    }
    factory<CheckedTicketsInteractor> {
        CheckedTicketsInteractorImpl(repository = get())
    }
    factory<AllTicketsInteractor> {
        AllTicketsInteractorImpl(repository = get())
    }
}
