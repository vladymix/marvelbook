package com.altamirano.fabricio.marvelbook.interfaces

import com.altamirano.fabricio.marvelbook.models.Character

interface ICharactersView {
    fun showResults(charactersList:List<Character>)
    fun errorOperation(stringRes:Int)
}