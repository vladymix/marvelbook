
package com.altamirano.dagger.di.component

import com.altamirano.dagger.di.module.ActivityModule
import com.altamirano.dagger.di.socope.PerActivity
import com.altamirano.dagger.ui.base.BaseActivity
import com.altamirano.dagger.ui.details.DetailsDialogView
import com.altamirano.dagger.ui.list.CharactersActivity
import dagger.Component

@PerActivity
@Component( dependencies = arrayOf(ApplicationComponent::class, ActivityModule::class))
interface ActivityComponent {

    //Exposed to sub-graphs.
    fun activity():BaseActivity

    fun inject(base: CharactersActivity) // Need to sub-graphs
    fun inject(base: DetailsDialogView) // Need to sub-graphs

}