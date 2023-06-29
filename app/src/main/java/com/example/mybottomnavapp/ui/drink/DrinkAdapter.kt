package com.example.mybottomnavapp.ui.drink

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybottomnavapp.R
import com.example.mybottomnavapp.data.model.drink.Drink
import com.example.mybottomnavapp.databinding.ItemDrinkBinding

class DrinkAdapter(val drinks: List<Drink?>) : RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemDrinkBinding.bind(view)
        fun updateUI(drink: Drink) {
            binding.apply {
                tvDrink.text = drink.strDrink
                tvCategory.text = drink.strCategory
                tvAlcoholic.text = drink.strAlcoholic
                Glide.with(view)
                    .load(drink.strDrinkThumb)
                    .placeholder(R.drawable.heart)
                    .into(ivDrink)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_drink, parent, false)
        )
    }

    override fun getItemCount() = drinks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        drinks[position]?.let { holder.updateUI(it) }
    }
}