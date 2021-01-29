
package com.altamirano.dagger.di.module

import android.content.Context
import com.altamirano.dagger.MarvelApplication
import com.altamirano.dagger.di.socope.NamedMainThread
import com.altamirano.dagger.di.socope.NamedWorkerThread
import com.altamirano.dagger.threading.executor.Executor
import com.altamirano.dagger.threading.executor.MainThreadExecutor
import com.altamirano.dagger.threading.executor.WorkerThreadExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: MarvelApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext():Context = application

    @Provides
    @Singleton
    @NamedWorkerThread
    fun provideThreadExecutor(workerThreadExecutor: WorkerThreadExecutor?): Executor? {
        return workerThreadExecutor
    }

    @Provides
    @Singleton
    @NamedMainThread
    fun provideMainThread(mainThreadExecutor: MainThreadExecutor?): Executor? {
        return mainThreadExecutor
    }
}