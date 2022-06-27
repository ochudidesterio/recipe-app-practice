package com.virus.recipeapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "categoryItems")

data class CategoryItems(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @Expose
    @ColumnInfo(name = "idCategory")
    @SerializedName("idCategory")
    var idCategory: String,
    @Expose
    @ColumnInfo(name = "strCategory")
    @SerializedName("strCategory")
    var strCategory: String,
    @Expose
    @ColumnInfo(name = "strCategoryThumb")
    @SerializedName("strCategoryThumb")
    var strCategoryThumb: String,
    @Expose
    @ColumnInfo(name = "strCategoryDescription")
    @SerializedName("strCategoryDescription")
    var strCategoryDescription: String

)
