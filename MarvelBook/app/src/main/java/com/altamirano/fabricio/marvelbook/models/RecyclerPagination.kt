package com.altamirano.fabricio.marvelbook.models

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerPagination: RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if(dy>0){
            recyclerView.layoutManager?.let { glm->
                val childCount =  glm.childCount
                val totalItemsCount =  glm.itemCount
                val visibleItems =  (glm as GridLayoutManager).findFirstCompletelyVisibleItemPosition()
                if ((visibleItems + childCount) >= totalItemsCount){
                    this.positionEnd()
                }

            }
        }
        super.onScrolled(recyclerView, dx, dy)
    }

    abstract fun positionEnd()
}