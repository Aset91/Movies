package com.example.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.GenreItemBinding

class GenresAdapter() :
    RecyclerView.Adapter<GenresAdapter.GenresHolder>() {

    var onItemClick: ((String) -> Unit)? = null

    var genresList: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class GenresHolder(val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val genre = binding.genreTv
        val genreContainer = binding.genreContainer
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GenreItemBinding.inflate(layoutInflater, parent, false)
        return GenresHolder(binding)
    }

    override fun onBindViewHolder(holder: GenresHolder, position: Int) {
        val chosenGenre = genresList[position]
        holder.genre.text = chosenGenre
        holder.genreContainer.setOnClickListener {
            onItemClick?.invoke(chosenGenre)
        }
    }

    override fun getItemCount(): Int {
        return genresList.size
    }
}
