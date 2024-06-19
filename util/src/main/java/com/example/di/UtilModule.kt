package com.example.di

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val SETTINGS_APP = "setting_app"

val utilModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            SETTINGS_APP,
            AppCompatActivity.MODE_PRIVATE
        )
    }
}