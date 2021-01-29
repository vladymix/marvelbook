
package com.altamirano.dagger.di.component

import com.altamirano.dagger.api.IApiService
import com.altamirano.dagger.di.module.ApplicationModule
import com.altamirano.dagger.di.module.NetworkModule
import com.altamirano.dagger.di.module.RepositoryModule
import com.altamirano.dagger.domain.repository.CharactersRepository
import com.altamirano.dagger.threading.Threading
import com.altamirano.dagger.ui.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class, RepositoryModule::class))
interface ApplicationComponent {

    fun inject(baseActivity: BaseActivity)

    fun threading():Threading

    fun apiService():IApiService

    fun charactersRepository():CharactersRepository

}