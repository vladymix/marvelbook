
package com.altamirano.dagger.domain.usecase.base

import com.altamirano.dagger.exceptions.ConnectionException
import com.altamirano.dagger.exceptions.MarvelApiException
import com.altamirano.dagger.threading.Threading


abstract class BaseUseCase<Param, Callback : BaseUseCaseCallback?>(protected  val threading: Threading) {

    protected abstract fun runUseCase(param: Param, callback: Callback)

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

}