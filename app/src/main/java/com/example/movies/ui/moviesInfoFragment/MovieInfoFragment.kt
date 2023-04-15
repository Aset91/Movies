package com.example.movies.ui.moviesInfoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movies.databinding.MovieInfoFragmentBinding
import com.example.movies.response.Film
import com.example.movies.rest.MoviesRepository
import com.example.movies.rest.MoviesRepository.Companion.BASE_URL

class MovieInfoFragment : Fragment() {
    lateinit var viewModel: MovieInfoFragmentViewModel
    lateinit var binding: MovieInfoFragmentBinding
    private val args: MovieInfoFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieInfoFragmentViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieName.text = args.film.name
        binding.description.text = args.film.description
        binding.year.text = args.film.year.toString()
        binding.rating.text = args.film.rating.toString()
        binding.localizedName.text = args.film.localized_name
        binding.id.text = args.film.id.toString()
        Glide.with(this).load(args.film.image_url).into(binding.movieImage)
    }
}