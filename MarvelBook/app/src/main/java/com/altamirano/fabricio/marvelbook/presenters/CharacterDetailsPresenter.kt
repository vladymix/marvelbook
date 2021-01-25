package com.altamirano.fabricio.marvelbook.presenters

import android.widget.ImageView
import com.altamirano.fabricio.marvelbook.Constants.getAsUrl
import com.altamirano.fabricio.marvelbook.R
import com.altamirano.fabricio.marvelbook.exceptions.MarvelApiException
import com.altamirano.fabricio.marvelbook.interactor.CharacterDetailsInteractor
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersDetailsPresenter
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersDetailsView
import com.altamirano.fabricio.marvelbook.models.Character
import com.altamirano.fabricio.marvelbook.models.Thumbnail
import com.altamirano.fabricio.marvelbook.services.*
import com.bumptech.glide.Glide

class CharacterDetailsPresenter(private val view: ICharactersDetailsView,service: IMarvelService) :

    ICharactersDetailsPresenter {

    private val interactor: CharacterDetailsInteractor

    init {
        interactor = CharacterDetailsInteractor(this,service)
    }

    override fun showResult(character: Character) {
        view.showResult(character)
    }

    override fun invalidOperation(t: Throwable) {
        val idMessage = when (t) {
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

    override fun loadCharacter(id: String) {
        interactor.loadCharacter(id)
    }

    override fun loadImageInto(image: Thumbnail, imageView:ImageView?) {
        imageView?.let {
            Glide.with(imageView.context).load(image.getAsUrl()).into(imageView)
        }
    }
}