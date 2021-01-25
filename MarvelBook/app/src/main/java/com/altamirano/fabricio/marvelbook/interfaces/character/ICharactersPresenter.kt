package com.altamirano.fabricio.marvelbook.interfaces.character

import com.altamirano.fabricio.marvelbook.models.Character

interface ICharactersPresenter {
    fun showResults(charactersList:List<Character>)
    fun invalidOperation(t: Throwable)
    fun loadCharacters(nameStartsWith:String?=null)
}