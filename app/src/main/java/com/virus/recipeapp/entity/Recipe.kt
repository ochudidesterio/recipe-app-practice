package com.virus.recipeapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import java.io.Serializable

@Entity(tableName = "Recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @Expose
    @ColumnInfo(name = "dishname")
    var dishName : String

): Serializable