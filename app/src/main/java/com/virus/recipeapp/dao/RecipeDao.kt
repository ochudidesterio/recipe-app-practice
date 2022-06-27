package com.virus.recipeapp.dao

import androidx.room.*
import com.virus.recipeapp.entity.Categories
import com.virus.recipeapp.entity.CategoryItems
import com.virus.recipeapp.entity.MealItems
import com.virus.recipeapp.entity.Recipe

@Dao
interface RecipeDao {
    @Query("select * from categoryItems order by id desc")
    suspend fun allCategoryItems(): List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCategoryItem(categories: CategoryItems)

    @Query("delete from categoryItems")
    suspend fun clearDb()

    @Query("select * from mealitems where categoryName =:categoryname order by id desc")
    suspend fun getMealsBySpecificCategory(categoryname: String): List<MealItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealItem(categories: MealItems)
}