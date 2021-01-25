package com.altamirano.fabricio.marvelbook.interfaces.character

interface ICharactersInteractor {
    fun loadCharacters(nameStartsWith:String?=null)
}