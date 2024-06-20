package com.example.tickets.di

import com.example.tickets.presentation.viewmodels.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ticketsViewModelModule = module {
    viewModel<MainViewModel> {
        MainViewModel(interactor = get())
    }
}