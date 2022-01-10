package com.thiru.cleanarchisample

import android.app.Application
import com.thiru.cleanarchisample.di.injectFeature
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            injectFeature()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}