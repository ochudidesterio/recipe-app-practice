package com.virus.recipeapp.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.virus.recipeapp.entity.conveter.MeaListConverter
@Entity(tableName = "Meal")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name="meals")
    @SerializedName("meals")
    @Expose
    @TypeConverters(MeaListConverter::class)
    val meals: List<MealItems>? = null
)