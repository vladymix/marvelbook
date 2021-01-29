
package com.altamirano.dagger.threading.executor


import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import javax.inject.Inject

class MainThreadExecutor @Inject @VisibleForTesting(otherwise = VisibleForTesting.NONE) constructor() : Executor {
    private var mHandler: Handler? = null

    init {
        mHandler = Handler(Looper.getMainLooper())
    }

    override fun execute(runnable: Runnable) {
        mHandler?.post(runnable)
    }
}