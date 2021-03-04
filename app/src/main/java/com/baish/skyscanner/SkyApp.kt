package com.baish.skyscanner

import android.app.Application
import com.baish.skyscanner.data.di.appModules
import com.baish.skyscanner.data.local.PreferenceHelper
import org.koin.android.ext.koin.androidContext

import org.koin.core.context.startKoin

class SkyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(applicationContext)
        startKoin {
            androidContext(this@SkyApp)
            modules(appModules)
        }
    }
}