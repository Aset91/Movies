package com.example.movies.ui.genresFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.adapters.GenresAdapter
import com.example.movies.adapters.MoviesAdapter
import com.example.movies.databinding.GenresFragmentBinding
import com.example.movies.response.Film
import com.example.movies.ui.moviesInfoFragment.MovieInfoFragmentArgs
import kotlinx.coroutines.launch

class GenresFragment : Fragment() {
    private lateinit var binding: GenresFragmentBinding
    private lateinit var viewModel: GenresFragmentViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[GenresFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GenresFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        viewModel.moviesListLiveData.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            initRecyclerView(it.films)
        }
    }

    private fun initRecyclerView(list: List<Film>) {
        val moviesRv = binding.moviesRv
        val genresRv = binding.genresRv
        moviesRv.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.moviesListLiveData.observe(viewLifecycleOwner) {
            moviesAdapter = MoviesAdapter().apply {
                dataList = it.films
                onItemClick = { film ->
                    val destination = GenresFragmentDirections.toMovieInfoFragment(film)
                    findNavController().navigate(destination)
                }
            }
            moviesRv.adapter = moviesAdapter


            buildSet {
                list.forEach { film ->
                    film.genres.forEach { genre ->
                        add(genre)
                    }
                }
            }.also {
                genresRv.apply {
                    adapter = GenresAdapter().apply {
                        genresList = it.toList()
                        onItemClick = { genre ->
                            val tempList = viewModel.moviesListLiveData.value?.films ?: emptyList()
                            moviesAdapter.dataList = tempList.filter { it.genres.contains(genre) }
                        }
                    }
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )

                }
            }
        }
    }
}