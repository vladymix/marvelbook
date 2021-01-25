package com.altamirano.fabricio.marvelbook.presenters

import com.altamirano.fabricio.marvelbook.R
import com.altamirano.fabricio.marvelbook.exceptions.MarvelApiException
import com.altamirano.fabricio.marvelbook.interactor.CharactersInteractor
import com.altamirano.fabricio.marvelbook.interfaces.character.ICharactersInteractor
import com.altamirano.fabricio.marvelbook.interfaces.character.ICharactersPresenter
import com.altamirano.fabricio.marvelbook.interfaces.character.ICharactersView
import com.altamirano.fabricio.marvelbook.models.Character
import com.altamirano.fabricio.marvelbook.services.*

class CharactersPresenter(val view: ICharactersView, service:IMarvelService) :
    ICharactersPresenter {

    private val interactor: ICharactersInteractor

    init {
        interactor = CharactersInteractor(this, service)
    }
    override fun showResults(charactersList: List<Character>) {
        view.showResults(charactersList)
    }

    override fun invalidOperation(t:Throwable) {
      val idMessage= when (t) {
          is MarvelApiException -> {
              when(t.code){
                  401->{
                      R.string.error_401
                  }
                  403->{
                      R.string.error_403
                  }
                  404->{
                      R.string.error_404
                  }
                  405->{
                      R.string.error_405
                  }
                  409->{
                      R.string.error_409
                  }
                  else ->{
                      R.string.error_generic
                  }
              }
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