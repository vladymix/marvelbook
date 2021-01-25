package com.altamirano.fabricio.marvelbook.interfaces.character

import com.altamirano.fabricio.marvelbook.models.Character

interface ICharactersView {
    fun showResults(charactersList:List<Character>)
    fun errorOperation(stringRes:Int)
}