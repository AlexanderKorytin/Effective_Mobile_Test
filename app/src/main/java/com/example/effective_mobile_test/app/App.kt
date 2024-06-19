package com.example.effective_mobile_test.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.di.networkModule
import com.example.di.utilModule
import com.example.tickets.di.aminDomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        startKoin {
            androidContext(this@App)
            modules(
                utilModule,
                networkModule,
                aminDomainModule,
            )
        }
    }
}
