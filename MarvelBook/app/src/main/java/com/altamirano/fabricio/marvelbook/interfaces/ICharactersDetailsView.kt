package com.altamirano.fabricio.marvelbook.interfaces

import android.graphics.Bitmap
import com.altamirano.fabricio.marvelbook.models.Character

interface ICharactersDetailsView {
    fun showResult(character:Character)
    fun errorOperation(stringRes:Int)
}