package com.virus.recipeapp.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

@Entity(tableName = "MealItems")
data class MealItems(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "idMeal")
    @SerializedName("idMeal")
    @Expose
    val idMeal: String,
    @ColumnInfo(name = "categoryName")
    val categoryName: String,
    @ColumnInfo(name = "strMeal")
    @SerializedName("strMeal")
    @Expose
    val strMeal: String,
    @ColumnInfo(name = "strMealThumb")
    @SerializedName("strMealThumb")
    @Expose
    val strMealThumb: String
)