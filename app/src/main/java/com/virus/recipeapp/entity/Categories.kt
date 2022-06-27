package com.virus.recipeapp.entity

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.virus.recipeapp.entity.conveter.CategoryListConverter

@Entity(tableName = "Categories")

data class Categories(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @Expose
    @ColumnInfo(name = "categories")
    @SerializedName("categories" )
    @TypeConverters(CategoryListConverter::class)
    var categories : List<CategoryItems>?=null
)
