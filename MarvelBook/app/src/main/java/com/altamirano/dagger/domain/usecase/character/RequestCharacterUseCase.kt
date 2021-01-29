package com.altamirano.dagger.domain.usecase.character

import com.altamirano.dagger.domain.repository.CharactersRepository
import com.altamirano.dagger.domain.usecase.base.BaseUseCase
import com.altamirano.dagger.domain.usecase.base.BaseUseCaseCallback
import com.altamirano.dagger.models.Character
import com.altamirano.dagger.threading.Threading
import javax.inject.Inject

class RequestCharacterUseCase @Inject constructor(threading: Threading,
    private var mRepository: CharactersRepository
) : BaseUseCase<String, RequestCharacterUseCase.Callback>(threading) {

    interface Callback : BaseUseCaseCallback {
        fun onSuccessRequestCharacterUseCase(character: Character)
        fun onErrorRequestCharactersUseCase()
    }

    override fun runUseCase(param: String, callback: Callback) {
        val data = mRepository.getCharacter(param)

        if (data == null) {
            sendToMainThread { callback.onErrorRequestCharactersUseCase() }
        } else {
            sendToMainThread { callback.onSuccessRequestCharacterUseCase(data) }
        }
    }
}