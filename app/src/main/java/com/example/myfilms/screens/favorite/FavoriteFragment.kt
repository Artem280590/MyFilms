package com.example.myfilms.screens.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myfilms.MAIN
import com.example.myfilms.R
import com.example.myfilms.databinding.FragmentFavoriteBinding
import com.example.myfilms.model.MoviesModelItem


class FavoriteFragment : Fragment() {

    private var mbinding: FragmentFavoriteBinding?= null
    private val binding get() = mbinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { FavoriteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mbinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[FavoriteFragmentViewModel::class.java]
        recyclerView = binding.rvFavorite
        recyclerView.adapter = adapter
        viewModel.getAllMovies().observe(viewLifecycleOwner) { list ->
            adapter.setList(list.asReversed())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }

    companion object{
        fun clickMovie(modelItem: MoviesModelItem){
            val bundle = Bundle()
            bundle.putSerializable("movie", modelItem)
            MAIN.navController.navigate(R.id.action_rootFragment_to_detailFragment, bundle)
        }
    }
}