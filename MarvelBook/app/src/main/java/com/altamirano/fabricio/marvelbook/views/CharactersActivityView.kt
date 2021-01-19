package com.altamirano.fabricio.marvelbook.views

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirano.fabricio.marvelbook.Constants
import com.altamirano.fabricio.marvelbook.R
import com.altamirano.fabricio.marvelbook.adapters.CharactersAdapter
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersPresenter
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersView
import com.altamirano.fabricio.marvelbook.models.Character
import com.altamirano.fabricio.marvelbook.models.RecyclerPagination
import com.altamirano.fabricio.marvelbook.presenters.CharactersPresenter
import com.altamirano.fabricio.marvelbook.services.IMarvelService
import com.altamirano.fabricio.marvelbook.services.MarvelService

class CharactersActivityView : AppCompatActivity(), ICharactersView {
    lateinit var presenter:ICharactersPresenter
    lateinit var adapter:CharactersAdapter

    private var progressIndicator: ProgressBar?=null
    private var lottieAnimation: View?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.adapter = CharactersAdapter()

        this.adapter.onItemSelected = {
            this.onItemSeleccted(it)
        }

        this.progressIndicator = findViewById(R.id.progressIndicator)
        this.lottieAnimation = findViewById(R.id.lottieAnimation)

        findViewById<RecyclerView>(R.id.recyclerView)?.let {
            it.adapter = this.adapter
            it.addOnScrollListener(object : RecyclerPagination() {
                override fun positionEnd() {
                    if(!isLoading()){
                        presenter.loadCharacters()
                        progressIndicator?.visibility = View.VISIBLE
                    }
                }
            })
        }
        presenter = CharactersPresenter(this, MarvelService.instance)
        presenter.loadCharacters()
    }

    fun isLoading():Boolean{
        progressIndicator?.let {
            return it.visibility == View.VISIBLE
        }
        return true
    }

    private fun onItemSeleccted(item:Character){
     val dialog=   DialogDetailsView().apply {
            this.arguments = Bundle().apply {
                this.putString(Constants.ID_CHARACTER, item.id)
                this.putString(Constants.TITLE_CHARACTER, item.name)
                this.putString(Constants.DESCRIPTION_CHARACTER, item.description)
            }
        }

        dialog.show(supportFragmentManager, "details")
    }

    override fun showResults(charactersList: List<Character>) {
        progressIndicator?.visibility = View.GONE
        lottieAnimation?.visibility = View.GONE
        adapter.setDataSource(charactersList)
    }

    override fun errorOperation(stringRes: Int) {
       Toast.makeText(this, stringRes,Toast.LENGTH_LONG).show()
    }
}