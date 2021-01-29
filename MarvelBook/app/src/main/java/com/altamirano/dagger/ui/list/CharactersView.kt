
package com.altamirano.dagger.ui.list

import com.altamirano.dagger.models.Character
import com.altamirano.dagger.ui.base.BaseView

interface CharactersView : BaseView {

    fun showResults(characters:List<Character>)
    fun showEmptyCharactersList()
    fun showEmptyState()
}