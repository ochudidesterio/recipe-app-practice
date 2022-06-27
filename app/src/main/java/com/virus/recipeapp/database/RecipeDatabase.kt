package com.virus.recipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.virus.recipeapp.dao.RecipeDao
import com.virus.recipeapp.entity.*
import com.virus.recipeapp.entity.conveter.CategoryListConverter
import com.virus.recipeapp.entity.conveter.MeaListConverter

@Database(entities = [Recipe::class,CategoryItems::class,Categories::class,Meal::class,MealItems::class],version = 1,exportSchema = false)
@TypeConverters(CategoryListConverter::class,MeaListConverter::class)
abstract class RecipeDatabase : RoomDatabase(){
    companion object{
        var recipeDatabase:RecipeDatabase? = null
        @Synchronized
        fun getDatabase(context: Context):RecipeDatabase{
            if(recipeDatabase == null){
                recipeDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).build()
            }
            return recipeDatabase!!
        }
    }
    abstract fun recipeDao():RecipeDao
}