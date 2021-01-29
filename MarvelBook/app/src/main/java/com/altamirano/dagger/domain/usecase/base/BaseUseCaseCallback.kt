
package com.altamirano.dagger.domain.usecase.base


interface BaseUseCaseCallback {
    fun onConnectionError()
    fun onShowErrorMessage(message: String?)
}