
package com.altamirano.dagger.ui.base


interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showConnectionError()
    fun showDefaultError(message:String?)
}