package com.adiluhung.sportify

import android.app.Application
import com.adiluhung.sportify.di.useCaseModule
import com.adiluhung.sportify.di.viewModelModule
import com.adiluhung.sportify.core.di.databaseModule
import com.adiluhung.sportify.core.di.networkModule
import com.adiluhung.sportify.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}