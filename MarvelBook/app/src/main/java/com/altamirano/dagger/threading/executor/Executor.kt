
package com.altamirano.dagger.threading.executor


interface Executor {
    /**
     * This method should call the run method and thus start the use case.
     * This should be called on a background thread as use cases might do lengthy operations.
     *
     * @param runnable The runnable to run
     */
    fun execute(runnable: Runnable)
}