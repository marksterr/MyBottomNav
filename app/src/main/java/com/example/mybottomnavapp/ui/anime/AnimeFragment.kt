package com.example.mybottomnavapp.ui.anime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mybottomnavapp.R
import com.example.mybottomnavapp.data.remote.ApiDetails
import com.example.mybottomnavapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AnimeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // layoutInflater // activity's inflator
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        loadData()

        binding.btnNextAnime.setOnClickListener {
            loadData()
        }

        // no internet permission in manifest file
        // dispatcher crashed - thread 0 failed -> run in background

        return binding.root
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.Main).launch {

            val result = ApiDetails.apiClient.getRandomAnime()
            binding.apply {
                tvAnime.text = result.data?.titleEnglish ?: result.data?.title
                tvEpisodeValue.text = result.data?.episodes.toString()
                tvGenreValue.text = result.data?.genres?.joinToString("-", "#")
            }

            Glide
                .with(requireContext())
                //                .load("https://images.dog.ceo/breeds/terrier-fox/n02095314_2162.jpg")
                .load(result.data?.images?.jpg?.imageUrl)
                //                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView)

        }
    }

}