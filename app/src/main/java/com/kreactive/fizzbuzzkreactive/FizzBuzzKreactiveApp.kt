package com.kreactive.fizzbuzzkreactive

import android.app.Application
import com.kreactive.fizzbuzzkreactive.app.injection.AppModule
import com.kreactive.fizzbuzzkreactive.home.injection.HomeModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class FizzBuzzKreactiveApp : Application() {


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(level = Level.ERROR)
            }
            androidContext(this@FizzBuzzKreactiveApp)
            modules(
                AppModule,
                HomeModule,
            )
        }

    }

}
