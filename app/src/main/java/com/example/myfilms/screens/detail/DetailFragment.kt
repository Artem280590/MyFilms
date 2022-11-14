package com.example.myfilms.screens.detail

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myfilms.MAIN
import com.example.myfilms.R
import com.example.myfilms.SaveShared
import com.example.myfilms.databinding.FragmentDetailBinding
import com.example.myfilms.model.MoviesModelItem
import java.io.Serializable


class DetailFragment : Fragment() {


    private var mbinding: FragmentDetailBinding?= null
    private val binding get() = mbinding!!
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mbinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val currentMovie = arguments?.customGetSerializable<MoviesModelItem>("movie")
        val valueBoolean = SaveShared.getFavorite(MAIN, currentMovie?.id.toString())

        if (isFavorite != valueBoolean){
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite)
        }

        Glide.with(MAIN)
            .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2${currentMovie?.poster_path}")
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imgDetail)
        binding.tvTitle.text = currentMovie?.title
        binding.tvDate.text = currentMovie?.release_date
        binding.tvDescription.text = currentMovie?.overview
        binding.imgDetailFavorite.setOnClickListener{
            if (isFavorite == valueBoolean){
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                SaveShared.setFavorite(MAIN, currentMovie?.id.toString(), true)
                viewModel.insert(currentMovie!!)
                isFavorite = true
            }else{
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite)
                SaveShared.setFavorite(MAIN, currentMovie?.id.toString(), false)
                viewModel.delete(currentMovie!!)
                isFavorite = false
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }


    @Suppress("DEPRECATION")
    inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializable(key, T::class.java)
        } else {
            getSerializable(key) as? T
        }
    }


}