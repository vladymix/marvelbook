package com.altamirano.fabricio.marvelbook.presenters

import com.altamirano.fabricio.marvelbook.R
import com.altamirano.fabricio.marvelbook.interactor.CharactersInteractor
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersInteractor
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersPresenter
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersView
import com.altamirano.fabricio.marvelbook.models.Character
import com.altamirano.fabricio.marvelbook.services.*

class CharactersPresenter(val view:ICharactersView,  service:IMarvelService) : ICharactersPresenter {

    private val interactor:ICharactersInteractor

    init {
        interactor = CharactersInteractor(this, service)
    }
    override fun showResults(charactersList: List<Character>) {
        view.showResults(charactersList)
    }

    override fun invalidOperation(t:Throwable) {
      val idMessage= when (t) {
          is Exception401 -> {
              R.string.error_401
          }
          is Exception403 -> {
              R.string.error_403
          }
          is Exception405 -> {
              R.string.error_405
          }
          is Exception409 -> {
              R.string.error_409
          }
          else -> {
              R.string.error_generic
          }
      }
        view.errorOperation(idMessage)
    }

    override fun loadCharacters(nameStartsWith: String?) {
        interactor.loadCharacters(nameStartsWith)
    }
}