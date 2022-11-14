package com.example.myfilms.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myfilms.MAIN
import com.example.myfilms.R
import com.example.myfilms.databinding.FragmentMainBinding
import com.example.myfilms.model.MoviesModelItem


class MainFragment : Fragment(){

    private var mbinding: FragmentMainBinding ?= null
    private val binding get() = mbinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { MainAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mbinding = FragmentMainBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        viewModel.initDataBase()
        recyclerView = binding.rvMain
        recyclerView.adapter = adapter
        try {
            viewModel.getMyMoviesRetrofit()
            viewModel.myMovies.observe(viewLifecycleOwner) { list ->
                adapter.setList(list.body()!!.results) }

        }catch (e: Exception){
            Toast.makeText(MAIN, e.message, Toast.LENGTH_SHORT).show()
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