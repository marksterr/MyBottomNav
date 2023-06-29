package com.example.mybottomnavapp.ui.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mybottomnavapp.R
import com.example.mybottomnavapp.data.remote.ApiDetails
import com.example.mybottomnavapp.databinding.FragmentListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    var characterCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        loadData()

        binding.btnNextCharacter.setOnClickListener {
            characterCount++
            loadData()
        }

        return binding.root
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.Main).launch {

            val result = ApiDetails.rickClient.getRickCharacter(characterCount)
            binding.tvList.text = result.name
            binding.rvEpisodes.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = EpisodeAdapter(result.episode)
            }

            Glide
                .with(requireContext())
                .load(result.image)
    //                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView)
        }
    }
}