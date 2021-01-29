package com.altamirano.dagger.di.module


import com.altamirano.dagger.di.socope.PerActivity
import com.altamirano.dagger.ui.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule constructor(private var baseActivity: BaseActivity) {

    @Provides
    @PerActivity
    fun provideActivity():BaseActivity{
        return baseActivity
    }
}