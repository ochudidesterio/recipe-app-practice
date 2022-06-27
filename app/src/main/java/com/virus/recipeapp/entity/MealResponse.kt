package com.virus.recipeapp.entity


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class MealResponse(
    @SerializedName("meals")
    @Expose
    val meals: List<MealResponseItem>
)