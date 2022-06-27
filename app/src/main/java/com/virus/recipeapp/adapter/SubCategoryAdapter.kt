package com.virus.recipeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virus.recipeapp.databinding.ItemRvSubCategoryBinding
import com.virus.recipeapp.entity.MealItems

class SubCategoryAdapter(var subCategoryList:ArrayList<MealItems>): RecyclerView.Adapter<SubCategoryAdapter.SubCategoryHolder>() {
    private lateinit var binding: ItemRvSubCategoryBinding
    private lateinit var inflater: LayoutInflater
    private var context : Context ? =null
    var listener : OnClickCategoryItem? = null

    fun  setClickListener(liste : OnClickCategoryItem){
        listener = liste
    }

    inner class SubCategoryHolder(b: ItemRvSubCategoryBinding): RecyclerView.ViewHolder(b.root){
        fun bind(recipe: MealItems) {
           binding.tvDishName.text = recipe.strMeal
            Glide.with(context!!).load(recipe.strMealThumb).centerCrop()
                .into(binding.imgDish)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryHolder {
        inflater = LayoutInflater.from(parent.context)
        context = parent.context
        binding= ItemRvSubCategoryBinding.inflate(inflater,parent,false)
        return SubCategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: SubCategoryHolder, position: Int) {
        holder.bind(subCategoryList[position])
        holder.itemView.setOnClickListener {
            listener!!.onClickItem(subCategoryList[position].idMeal)
        }
    }

    override fun getItemCount(): Int {
     return  subCategoryList.size
    }
    interface OnClickCategoryItem{
        fun onClickItem(id:String)
    }
}