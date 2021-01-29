package com.altamirano.dagger

import android.app.Application
import android.content.Context
import com.altamirano.dagger.di.component.ApplicationComponent
import com.altamirano.dagger.di.component.DaggerApplicationComponent
import com.altamirano.dagger.di.module.ApplicationModule
import com.altamirano.dagger.di.module.NetworkModule
import com.altamirano.dagger.di.module.RepositoryModule

class MarvelApplication : Application() {

    private var mApplicationComponent: ApplicationComponent? = null

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        this.initializeInjector()
    }

    private fun initializeInjector() {
        this.mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule()).build()
    }

    fun getApplicationComponent():ApplicationComponent = this.mApplicationComponent!!

}