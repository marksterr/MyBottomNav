package com.example.mybottomnavapp.ui.character

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybottomnavapp.R
import com.example.mybottomnavapp.databinding.ItemEpisodeBinding

class EpisodeAdapter(val episodes: List<String?>) : RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemEpisodeBinding.bind(view)
        fun updateUI(episode: String) {
            binding.apply {
                tvEpisode.text = extractEpisodeNumber(episode)
                tvEpisode.setOnClickListener {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(episode)
                    tvEpisode.context.startActivity(i)
                }
            }
        }

        private fun extractEpisodeNumber(url: String): String {
            val splitUrl = url.split("/")
            return "Episode ${splitUrl.last()}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeAdapter.ViewHolder {
        return EpisodeAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false)
        )
    }

    override fun getItemCount() = episodes.size

    override fun onBindViewHolder(holder: EpisodeAdapter.ViewHolder, position: Int) {
        episodes[position]?.let { holder.updateUI(it) }
    }
}