package com.be2.test3.ui

import android.view.View
import com.be2.test3.models.Movie

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, movie: Movie)
}

