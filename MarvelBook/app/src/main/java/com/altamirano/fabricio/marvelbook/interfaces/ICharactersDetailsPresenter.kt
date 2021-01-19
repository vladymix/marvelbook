package com.altamirano.fabricio.marvelbook.interfaces

import android.widget.ImageView
import com.altamirano.fabricio.marvelbook.models.Character
import com.altamirano.fabricio.marvelbook.models.Thumbnail

interface ICharactersDetailsPresenter {
    fun showResult(character:Character)
    fun invalidOperation(t: Throwable)
    fun loadCharacter(id:String)

    fun loadImageInto(image: Thumbnail, imageView: ImageView?)
}