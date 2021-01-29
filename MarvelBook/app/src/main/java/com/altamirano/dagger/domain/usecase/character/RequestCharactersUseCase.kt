
package com.altamirano.dagger.domain.usecase.character


import com.altamirano.dagger.models.Character
import com.altamirano.dagger.domain.repository.CharactersRepository
import com.altamirano.dagger.domain.usecase.base.BaseUseCase
import com.altamirano.dagger.domain.usecase.base.BaseUseCaseCallback

import com.altamirano.dagger.threading.Threading
import javax.inject.Inject

class RequestCharactersUseCase  @Inject constructor(threading: Threading, private var mRepository: CharactersRepository) : BaseUseCase<Int, RequestCharactersUseCase.Callback>(threading) {

    interface Callback : BaseUseCaseCallback {
        fun onSuccessRequestCharactersUseCase(characters: ArrayList<Character>)
        fun onErrorRequestCharactersUseCase()
    }

    override fun runUseCase(param: Int, callback: Callback) {
        val data =   mRepository.getCharactes(param)

        if(data.isNullOrEmpty()){
            sendToMainThread{ callback.onErrorRequestCharactersUseCase() }
        }else{
            sendToMainThread{ callback.onSuccessRequestCharactersUseCase(data)}
        }
    }
}