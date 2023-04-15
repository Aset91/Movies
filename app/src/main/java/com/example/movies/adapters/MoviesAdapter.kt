package com.example.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.FilmItemBinding
import com.example.movies.response.Film

class MoviesAdapter() :
    RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    var onItemClick: ((Film) -> Unit)? = null

    var dataList: List<Film> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MovieHolder(val binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val movieName = binding.movieName
        val movieImage = binding.movieImage
        val movieContainer = binding.movieContainer

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FilmItemBinding.inflate(layoutInflater, parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val currentItem = dataList[position]
        holder.movieName.text = currentItem.name
        Glide.with(holder.binding.root.context)
            .load(currentItem.image_url)
            .into(holder.movieImage)
        holder.movieContainer.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}