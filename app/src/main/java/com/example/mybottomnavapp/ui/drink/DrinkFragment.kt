package com.example.mybottomnavapp.ui.drink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybottomnavapp.data.remote.ApiDetails
import com.example.mybottomnavapp.databinding.FragmentDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class DrinkFragment : Fragment() {

    private var currentDrink: String = "cocktail"
    lateinit var binding: FragmentDetailBinding
    private val drinkList = listOf<String>(
        "cocktail",
        "vodka",
        "rum",
        "whiskey",
        "margarita",
        "wine",
        "cognac",
        "beer",
        "cider",
        "gin"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        loadData()

        binding.btnNextDrink.setOnClickListener {
            val index = Random.nextInt(drinkList.size - 1)
            currentDrink = drinkList[index]
            loadData()
        }

        return binding.root
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.Main).launch {

            val result = ApiDetails.drinkClient.getDrink(currentDrink)
            binding.rvDrinks.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = DrinkAdapter(result.drinks)
            }


        }
    }
}