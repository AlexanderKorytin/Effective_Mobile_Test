package com.example.effective_mobile_test.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.effective_mobile_test.R

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}
