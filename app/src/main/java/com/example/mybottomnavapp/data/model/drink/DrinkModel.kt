package com.example.mybottomnavapp.data.model.drink


import com.google.gson.annotations.SerializedName

data class DrinkModel(
    @SerializedName("drinks")
    val drinks: List<Drink>
)