package com.example.savepicture.main

import android.app.Application
import com.example.savepicture.di.listModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listModules)
        }
    }
}