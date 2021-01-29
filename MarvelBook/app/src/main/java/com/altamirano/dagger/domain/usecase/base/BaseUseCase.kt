
package com.altamirano.dagger.domain.usecase.base

import com.altamirano.dagger.exceptions.ConnectionException
import com.altamirano.dagger.exceptions.MarvelApiException
import com.altamirano.dagger.threading.Threading


abstract class BaseUseCase<Param, Callback : BaseUseCaseCallback?>(threading: Threading) {

    protected val threading: Threading

    var isCanceled = false
        private set

    var isRunning = false
        private set

    protected fun onStart() {
        isRunning = true
        isCanceled = false
    }

    fun cancel() {
        if (isRunning) {
            isCanceled = true
            isRunning = false
        }
    }

    protected fun onFinished() {
        isRunning = false
        isCanceled = false
    }

    protected abstract fun runUseCase(param: Param, callback: Callback)

    fun execute(params: Param, callback: Callback) {

        onStart()
        threading.executeOnWorkingThread {
            try {
                runUseCase(params, callback)
            } catch (e: ConnectionException) {
                sendToMainThread { callback!!.onConnectionError() }
            } catch (e: MarvelApiException) {
                sendToMainThread {
                    callback!!.onShowErrorMessage(e.message)
                }
            }
        }
        onFinished()
    }

    protected fun sendToMainThread(r: Runnable?) {
        threading.executeOnMainThread(r)
    }

    init {
        this.threading = threading
    }
}