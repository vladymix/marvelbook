package com.altamirano.dagger.ui.details

import android.widget.ImageView
import com.altamirano.dagger.domain.usecase.character.RequestCharacterUseCase
import com.altamirano.dagger.models.Character
import com.altamirano.dagger.models.Thumbnail
import com.altamirano.dagger.threading.Threading
import com.altamirano.dagger.ui.base.BasePresenter
import com.altamirano.dagger.util.Constants.getAsUrl
import com.bumptech.glide.Glide
import javax.inject.Inject

class DetailsPresenter @Inject constructor(threading: Threading) :
    BasePresenter<DetailsView>(threading), RequestCharacterUseCase.Callback {

    @Inject
    lateinit var mUseCase: RequestCharacterUseCase

    fun loadCharacter(id: String) {
        mUseCase.execute(id, this)
    }

    fun loadImageInto(image: Thumbnail, imageView: ImageView?) {
        imageView?.let {
            Glide.with(imageView.context).load(image.getAsUrl()).into(imageView)
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
        TODO("Not yet implemented")
    }

    override fun onShowErrorMessage(message: String?) {
        TODO("Not yet implemented")
    }
}