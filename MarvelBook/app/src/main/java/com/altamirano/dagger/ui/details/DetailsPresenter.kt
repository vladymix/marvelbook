package com.altamirano.dagger.ui.details

import android.widget.ImageView
import android.widget.Toast
import com.altamirano.dagger.MarvelApplication
import com.altamirano.dagger.domain.usecase.character.RequestCharacterUseCase
import com.altamirano.dagger.models.Character
import com.altamirano.dagger.models.Thumbnail
import com.altamirano.dagger.threading.Threading
import com.altamirano.dagger.ui.base.BasePresenter
import com.altamirano.dagger.util.Constants.getAsUrl
import com.altamirano.fabricio.marvelbook.R
import com.bumptech.glide.Glide
import javax.inject.Inject

class DetailsPresenter @Inject constructor(threading: Threading) : BasePresenter<DetailsView>(threading), RequestCharacterUseCase.Callback {

    @Inject
    lateinit var mUseCase: RequestCharacterUseCase

    fun loadCharacter(id: String) {
        mUseCase.execute(id, this)
    }

    fun loadImageInto(image: Thumbnail, imageView: ImageView?) {
        imageView?.let {
            Glide.with(it.context).load(image.getAsUrl()).into(it)
        }
    }

    override fun onViewAttached(view: DetailsView) {
        super.setView(view)
    }

    override fun onViewDetached() {
        super.setView(null)
    }

    override fun onSuccessRequestCharacterUseCase(character: Character) {
        viewOrThrow.showResult(character)
    }

    override fun onErrorRequestCharactersUseCase() {
        Toast.makeText(
            MarvelApplication.context,
            MarvelApplication.context?.resources?.getString(R.string.error_generic),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onShowErrorMessage(message: String?) {
        viewOrThrow.showDefaultError(message)
    }
}