
package com.altamirano.dagger.threading.executor


import androidx.annotation.VisibleForTesting
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class WorkerThreadExecutor @Inject @VisibleForTesting(otherwise = VisibleForTesting.NONE) constructor() : Executor {


    private val CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors()
    private val MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors()
    private val KEEP_ALIVE_TIME = 120
    private val TIME_UNIT = TimeUnit.SECONDS
    private val WORK_QUEUE: BlockingQueue<Runnable> = LinkedBlockingQueue()

    private var mThreadPoolExecutor: ThreadPoolExecutor? = null

    init {

        val keepAlive: Long = KEEP_ALIVE_TIME.toLong()
        mThreadPoolExecutor = ThreadPoolExecutor(
           CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            keepAlive,
            TIME_UNIT,
            WORK_QUEUE
        )
    }

    override fun execute(runnable: Runnable) {
        mThreadPoolExecutor?.submit(runnable)
    }
}