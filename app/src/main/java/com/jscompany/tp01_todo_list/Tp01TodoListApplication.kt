package com.jscompany.tp01_todo_list

import android.app.Application
import com.jscompany.tp01_todo_list.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Tp01TodoListApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //TODO koin Trigger
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Tp01TodoListApplication)
            modules(appModule)
        }
    }
}