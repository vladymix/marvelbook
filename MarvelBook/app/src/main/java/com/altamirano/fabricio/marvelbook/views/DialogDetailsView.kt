package com.altamirano.fabricio.marvelbook.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirano.fabricio.marvelbook.Constants
import com.altamirano.fabricio.marvelbook.Constants.setAsShowed
import com.altamirano.fabricio.marvelbook.R
import com.altamirano.fabricio.marvelbook.adapters.AdapterItems
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersDetailsPresenter
import com.altamirano.fabricio.marvelbook.interfaces.ICharactersDetailsView
import com.altamirano.fabricio.marvelbook.interfaces.IFragmentListener
import com.altamirano.fabricio.marvelbook.models.Character
import com.altamirano.fabricio.marvelbook.presenters.CharacterDetailsPresenter
import com.altamirano.fabricio.marvelbook.services.MarvelService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogDetailsView : BottomSheetDialogFragment(), ICharactersDetailsView {

    lateinit var presenter: ICharactersDetailsPresenter

    var mImage: ImageView? = null
    var mTitle: TextView? = null
    var mDescription: TextView? = null

    var mAdapterComics: AdapterItems?=null
    var mAdapterSeries: AdapterItems?=null
    var interactor:IFragmentListener<Character>?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is IFragmentListener<*>){
            interactor = context as IFragmentListener<Character>
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false).apply {
            mImage = this.findViewById(R.id.imgCharacter)
            mTitle = this.findViewById(R.id.tvTitle)
            mDescription = this.findViewById(R.id.tvDescription)
            mAdapterComics = AdapterItems(R.drawable.ic_comic)
            mAdapterSeries = AdapterItems(R.drawable.ic_series)

            this.findViewById<RecyclerView>(R.id.recyclerComics)?.apply {
                this.layoutManager = LinearLayoutManager(this.context).apply {
                    this.orientation = LinearLayoutManager.HORIZONTAL
                }
                this.adapter = mAdapterComics
            }

            this.findViewById<RecyclerView>(R.id.recyclerSeries)?.apply {
                this.layoutManager = LinearLayoutManager(this.context).apply {
                    this.orientation = LinearLayoutManager.HORIZONTAL
                }
                this.adapter = mAdapterSeries
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = CharacterDetailsPresenter(this, MarvelService.instance)
        arguments?.getString(Constants.ID_CHARACTER)?.let { value ->
            presenter.loadCharacter(value)
        }

    }

    override fun showResult(character: Character) {

        this.context?.setAsShowed(character.id!!)
        interactor?.onInteraction(character)

        mTitle?.let {
            it.text = character.name
        }

        mDescription?.let {
            it.text = character.description
        }

        character.thumbnail?.let {
            presenter.loadImageInto(it, mImage)
        }

        character.comics?.items?.let {
            mAdapterComics?.setDataSource(it)
        }
        character.series?.items?.let {
            mAdapterSeries?.setDataSource(it)
        }
    }

    override fun errorOperation(stringRes: Int) {
        Toast.makeText(this.context, stringRes, Toast.LENGTH_LONG).show()
    }
}