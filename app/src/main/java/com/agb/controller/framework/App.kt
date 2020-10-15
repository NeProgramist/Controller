package com.agb.controller.framework

import android.app.Application
import com.agb.controller.framework.datasource.local.PreferencesManager

class App: Application() {
    companion object {
        lateinit var preferencesManager: PreferencesManager
    }

    override fun onCreate() {
        super.onCreate()
        preferencesManager = PreferencesManager(applicationContext)
    }
}