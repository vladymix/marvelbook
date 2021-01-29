
package com.altamirano.dagger.ui.list


import com.altamirano.dagger.domain.usecase.character.RequestCharactersUseCase
import com.altamirano.dagger.exceptions.MarvelApiException
import com.altamirano.dagger.models.Character
import com.altamirano.dagger.threading.Threading
import com.altamirano.dagger.ui.base.BasePresenter
import com.altamirano.fabricio.marvelbook.R
import javax.inject.Inject

class CharactersPresenter @Inject constructor(threading: Threading) : BasePresenter<CharactersView>(threading), RequestCharactersUseCase.Callback {


    @Inject
    lateinit var mUseCase: RequestCharactersUseCase

    // private val interactor:ICharactersInteractor

    fun invalidOperation(t: Throwable) {
        val idMessage = when (t) {
            is MarvelApiException -> {
                when (t.code) {
                    401 -> {
                        R.string.error_401
                    }
                    403 -> {
                        R.string.error_403
                    }
                    404 -> {
                        R.string.error_404
                    }
                    405 -> {
                        R.string.error_405
                    }
                    409 -> {
                        R.string.error_409
                    }
                    else -> {
                        R.string.error_generic
                    }
                }
            }
            else -> {
                R.string.error_generic
            }
        }
    }


    fun loadCharacters(offset: Int) {

        mUseCase.execute(offset, this)
    }

    override fun onViewAttached(view: CharactersView) {
        super.setView(view)
        viewOrThrow.showLoading()
        mUseCase.execute(0, this)
    }

    override fun onViewDetached() {
        this.setView(null)
    }

    override fun onSuccessRequestCharactersUseCase(characters: ArrayList<Character>) {
        viewOrThrow.showResults(characters)
    }

    override fun onErrorRequestCharactersUseCase() {

    }

    override fun onShowErrorMessage(message: String?) {
        viewOrThrow.showDefaultError(message)
    }
}