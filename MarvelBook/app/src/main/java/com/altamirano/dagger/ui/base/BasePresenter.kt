
package com.altamirano.dagger.ui.base

import com.altamirano.dagger.threading.Threading

abstract class BasePresenter<T : BaseView> protected constructor(protected val threading: Threading) {


    protected var view: T? = null
        private set


    val viewOrThrow: T
        get() = view ?: throw IllegalStateException("view not attached")

    fun setView(view: T?) {
        this.view = view
    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * ([android.app.Activity] or [android.app.Fragment]) onStart() or onViewCreated() method.
     */
    abstract fun onViewAttached(view: T)

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * ([android.app.Activity] or [android.app.Fragment]) onDestroyView() method.
     */
    abstract fun onViewDetached()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * ([android.app.Activity] or [android.app.Fragment]) onDestroy() method.
     */
    fun destroy() {
        view = null
    }

    open fun onConnectionError() {
        if (view != null) {
            view?.showConnectionError()
        }
    }

    fun onDefaultError(message: String) {
        if (view != null) {
            view?.showDefaultError(message)
        }
    }
}