package com.altamirano.dagger.ui.details

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirano.dagger.models.Character
import com.altamirano.dagger.ui.base.BaseActivity
import com.altamirano.dagger.ui.base.BaseFragmentDialog
import com.altamirano.dagger.util.Constants
import com.altamirano.dagger.util.Constants.setAsShowed
import com.altamirano.fabricio.marvelbook.R
import com.altamirano.dagger.ui.base.IFragmentListener
import javax.inject.Inject

class DetailsDialogView : BaseFragmentDialog(), DetailsView {

    @Inject
    lateinit var presenter: DetailsPresenter

    private var mImage: ImageView? = null
    private var mTitle: TextView? = null
    private var mDescription: TextView? = null

    private var mDetailsComicsAdapter: DetailsItemsAdapter? = null
    private var mDetailsSeriesAdapter: DetailsItemsAdapter? = null

    private var listener: IFragmentListener<Character>? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (activity != null) {
            (activity as BaseActivity).getActivityComponent().inject(this)
        }
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IFragmentListener<*>) {
            listener = context as IFragmentListener<Character>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false).apply { setupView(this) }
    }

    private fun setupView(view: View) {
        view.apply {
            mImage = this.findViewById(R.id.imgCharacter)
            mTitle = this.findViewById(R.id.tvTitle)
            mDescription = this.findViewById(R.id.tvDescription)
            mDetailsComicsAdapter = DetailsItemsAdapter(R.drawable.ic_comic)
            mDetailsSeriesAdapter = DetailsItemsAdapter(R.drawable.ic_series)

            this.findViewById<RecyclerView>(R.id.recyclerComics)?.apply {
                this.layoutManager = LinearLayoutManager(this.context).apply {
                    this.orientation = LinearLayoutManager.HORIZONTAL
                }
                this.adapter = mDetailsComicsAdapter
            }

            this.findViewById<RecyclerView>(R.id.recyclerSeries)?.apply {
                this.layoutManager = LinearLayoutManager(this.context).apply {
                    this.orientation = LinearLayoutManager.HORIZONTAL
                }
                this.adapter = mDetailsSeriesAdapter
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewAttached(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDetached()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getString(Constants.ID_CHARACTER)?.let { value ->
            presenter.loadCharacter(value)
        }

    }

    override fun showResult(character: Character) {

        this.context?.setAsShowed(character.id!!)
        listener?.onInteraction(character)

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
            mDetailsComicsAdapter?.setDataSource(it)
        }
        character.series?.items?.let {
            mDetailsSeriesAdapter?.setDataSource(it)
        }
    }

    override fun showConnectionError() {

    }


    override fun showDefaultError(message: String?) {

    }

}