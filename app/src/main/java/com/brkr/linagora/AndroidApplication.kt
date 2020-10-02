package com.brkr.linagora

import android.app.Application
import com.brkr.linagora.di.RepositoryModule
import com.brkr.linagora.di.UseCaseModule
import com.brkr.linagora.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AndroidApplication)
            modules(listOf(RepositoryModule, UseCaseModule, ViewModelModule))
        }
    }
}
