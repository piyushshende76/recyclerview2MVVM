package com.be2.test3.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.be2.test3.models.Movie
import com.be2.test3.R
import com.be2.test3.databinding.RecyclerviewMovieBinding

class DataAdapter (
        private val movies: List<Movie>,
        private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<DataAdapter.MoviesViewHolder>(){

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_movie,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.recyclerviewMovieBinding.movie = movies[position]
        holder.recyclerviewMovieBinding.imageView.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerviewMovieBinding.imageView, movies[position])
        }
        holder.recyclerviewMovieBinding.laypost.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerviewMovieBinding.laypost, movies[position])
        }
    }


    inner class MoviesViewHolder(
        val recyclerviewMovieBinding: RecyclerviewMovieBinding
    ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)

}