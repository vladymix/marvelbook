package com.altamirano.fabricio.marvelbook.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.altamirano.fabricio.marvelbook.Constants.inflate
import com.altamirano.fabricio.marvelbook.R
import com.altamirano.fabricio.marvelbook.models.Item

class AdapterItems(val imageId:Int) : RecyclerView.Adapter<AdapterItems.ComicItemHolder>() {

    private var source = ArrayList<Item>()

    var onItemSelected:((Item)->Unit)?=null

    inner class ComicItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mName = itemView.findViewById<TextView>(R.id.tvNameComic)
        val mImageView = itemView.findViewById<ImageView>(R.id.imageView)

        fun bindValue(position: Int) {
            mImageView.setImageResource(imageId)
            itemView.setOnClickListener {
                onItemSelected?.invoke(source[adapterPosition])
            }

            source[position].let {
                mName.text = it.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicItemHolder =
        ComicItemHolder(parent.inflate(R.layout.item_comic))

    override fun onBindViewHolder(holder: ComicItemHolder, position: Int) {
        holder.bindValue(position)
    }

    fun setDataSource(list: List<Item>) {
        this.source.addAll(list)
        this.notifyDataSetChanged()

    }

    override fun getItemCount(): Int = source.size
}