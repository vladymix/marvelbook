package com.altamirano.fabricio.marvelbook.interfaces.details

import com.altamirano.fabricio.marvelbook.models.Character

interface ICharactersDetailsView {
    fun showResult(character:Character)
    fun errorOperation(stringRes:Int)
}