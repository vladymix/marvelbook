
package com.altamirano.dagger.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.altamirano.dagger.di.component.ActivityComponent
import com.altamirano.dagger.di.component.ApplicationComponent
import com.altamirano.dagger.di.component.DaggerActivityComponent
import com.altamirano.dagger.di.module.ActivityModule
import com.altamirano.dagger.MarvelApplication
import com.altamirano.fabricio.marvelbook.R

abstract class BaseActivity : AppCompatActivity(), BaseView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getApplicationComponent().inject(this)
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     **/
    protected fun getApplicationComponent(): ApplicationComponent {
        return (application as MarvelApplication).getApplicationComponent()
    }

    public fun getActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(ActivityModule(this))
            .build()
    }

    override fun showConnectionError() {
        this.hideLoading()
        Toast.makeText(baseContext, R.string.connection_error, Toast.LENGTH_LONG).show()
    }

    override fun showDefaultError(message: String?) {
        this.hideLoading()
        Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()

    }

}