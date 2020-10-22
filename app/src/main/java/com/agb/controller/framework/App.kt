package com.agb.controller.framework

import android.app.Application
import com.agb.controller.framework.datasource.local.PreferencesManager
import com.agb.controller.framework.datasource.remote.api.FlatsApi
import com.agb.controller.framework.datasource.remote.api.RoomsApi

class App: Application() {
    companion object {
        lateinit var preferencesManager: PreferencesManager
        val FlatsApi: FlatsApi = FlatsApi()
        val RoomsApi: RoomsApi = RoomsApi()
    }

    override fun onCreate() {
        super.onCreate()
        preferencesManager = PreferencesManager(applicationContext)
    }
}