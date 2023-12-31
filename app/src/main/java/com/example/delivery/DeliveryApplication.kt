package com.example.delivery

import android.app.Application
import android.content.Context
import com.example.delivery.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DeliveryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this@DeliveryApplication

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@DeliveryApplication)
            modules(appModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        appContext = null
    }

    companion object{
        var appContext: Context? = null
            private set
    }
}