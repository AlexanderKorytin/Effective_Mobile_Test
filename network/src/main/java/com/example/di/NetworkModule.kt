package com.example.di

import com.example.data.client.api.NetworkClient
import com.example.data.client.impl.RetrofitNetworkClient
import com.example.network.MokApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://run.mocky.io/v3/"

val networkModule = module {
    single<MokApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MokApi::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(service = get(), context = androidContext())
    }
}