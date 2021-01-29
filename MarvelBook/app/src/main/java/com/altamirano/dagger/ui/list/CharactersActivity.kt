
package com.altamirano.dagger.ui.list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar

import androidx.recyclerview.widget.RecyclerView
import com.altamirano.dagger.util.Constants
import com.altamirano.fabricio.marvelbook.R
import com.altamirano.dagger.ui.base.IFragmentListener
import com.altamirano.dagger.models.Character
import com.altamirano.dagger.models.RecyclerPagination
import com.altamirano.dagger.ui.base.BaseActivity
import com.altamirano.dagger.ui.details.DetailsDialogView
import javax.inject.Inject

class CharactersActivity : BaseActivity(), IFragmentListener<Character>, CharactersView {

    @Inject
    lateinit var presenter: CharactersPresenter

    private lateinit var adapter: CharactersAdapter
    private var progressIndicator: ProgressBar? = null
    private var lottieAnimation: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setupView()

        getActivityComponent().inject(this)

        presenter.onViewAttached(this)
    }

    private fun setupView() {

        this.adapter = CharactersAdapter()

        this.adapter.onItemSelected = {
            this.onItemSeleccted(it)
        }

        this.progressIndicator = findViewById(R.id.progressIndicator)
        this.lottieAnimation = findViewById(R.id.lottieAnimation)

        findViewById<RecyclerView>(R.id.recyclerView)?.let { recycler->
            recycler.adapter = this.adapter
            recycler.addOnScrollListener(object : RecyclerPagination() {
                override fun positionEnd() {
                    if (!isLoading()) {
                        presenter.loadCharacters(recycler.adapter!!.itemCount)
                        progressIndicator?.visibility = View.VISIBLE
                    }
                }
            })
        }

    }

    override fun showLoading() {
        progressIndicator?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressIndicator?.visibility = View.GONE
    }

    fun isLoading(): Boolean {
        progressIndicator?.let {
            return it.visibility == View.VISIBLE
        }
        return true
    }
    private fun onItemSeleccted(item: Character) {
        val dialog = DetailsDialogView().apply {
            this.arguments = Bundle().apply {
                this.putString(Constants.ID_CHARACTER, item.id)
                this.putString(Constants.TITLE_CHARACTER, item.name)
                this.putString(Constants.DESCRIPTION_CHARACTER, item.description)
            }
        }
        dialog.show(supportFragmentManager, "details")
    }

    override fun showResults(characters: List<Character>) {
        progressIndicator?.visibility = View.GONE
        lottieAnimation?.visibility = View.GONE
        adapter.setDataSource(characters)
    }

    override fun showEmptyCharactersList() {
        lottieAnimation?.visibility = View.VISIBLE
    }

    override fun showEmptyState() {

    }

    override fun onInteraction(obj: Character) {
        adapter.notifyItemPreviewed(obj)
    }
}