package com.altamirano.dagger.threading

import com.altamirano.dagger.di.socope.NamedMainThread
import com.altamirano.dagger.di.socope.NamedWorkerThread
import com.altamirano.dagger.threading.executor.Executor
import javax.inject.Inject

class Threading @Inject constructor( @NamedWorkerThread workerThreadExecutor: Executor?,
                                     @NamedMainThread mainExecutor: Executor?) {


    private var mWorkerThreadExecutor: Executor? = null
    private var mMainExecutor: Executor? = null


    init{
        mWorkerThreadExecutor = workerThreadExecutor
        mMainExecutor = mainExecutor
    }

    fun executeOnWorkingThread(r: Runnable?) {
        mWorkerThreadExecutor!!.execute(r!!)
    }

    fun executeOnMainThread(r: Runnable?) {
        mMainExecutor!!.execute(r!!)
    }
}