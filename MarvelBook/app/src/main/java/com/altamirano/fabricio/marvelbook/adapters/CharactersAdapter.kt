package com.altamirano.fabricio.marvelbook.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.altamirano.fabricio.marvelbook.Constants.getAsUrl
import com.altamirano.fabricio.marvelbook.Constants.inflate
import com.altamirano.fabricio.marvelbook.R
import com.altamirano.fabricio.marvelbook.models.Character
import com.bumptech.glide.Glide

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharactersHolder>() {

    private var source = ArrayList<Character>()

    var onItemSelected:((Character)->Unit)?=null

    inner class CharactersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitle = itemView.findViewById<TextView>(R.id.tvTitleCharacter)
        val mDescription = itemView.findViewById<TextView>(R.id.tvDescriptionCharacter)
        val imgCharacter = itemView.findViewById<ImageView>(R.id.imgCharacter)

        fun bindValue(position: Int) {
            itemView.setOnClickListener {
                onItemSelected?.invoke(source[adapterPosition])
            }

            source[position].let {
                mTitle.text = it.name
                mDescription.text = it.description

                it.thumbnail?.getAsUrl()?.let { url->
                    Glide.with(imgCharacter.context)
                        .load(url).placeholder(R.drawable.ic_heart)
                        .into(imgCharacter)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersHolder =
        CharactersHolder(parent.inflate(R.layout.item_character))

    override fun onBindViewHolder(holder: CharactersHolder, position: Int) {
        holder.bindValue(position)
    }

    fun setDataSource(list: List<Character>) {
        this.source.addAll(list)
        this.notifyDataSetChanged()

    }

    override fun getItemCount(): Int = source.size
}