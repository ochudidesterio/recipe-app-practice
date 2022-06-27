package com.virus.recipeapp.api

import com.virus.recipeapp.entity.Categories
import com.virus.recipeapp.entity.Meal
import com.virus.recipeapp.entity.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    fun getAllCategories():Call<Categories>
    @GET("filter.php")
    fun getMeals(@Query("c") categories: String):Call<Meal>
    @GET("lookup.php")
    fun getMealDetail(@Query("i") id: String):Call<MealResponse>
}