package com.altamirano.dagger.ui.details

import com.altamirano.dagger.models.Character
import com.altamirano.dagger.ui.base.BaseView

interface DetailsView : BaseView {
    fun showResult(character:Character)
}