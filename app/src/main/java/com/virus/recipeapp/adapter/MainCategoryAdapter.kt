package com.virus.recipeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virus.recipeapp.databinding.ItemRvMainCategoryBinding
import com.virus.recipeapp.entity.CategoryItems
import java.util.ArrayList

class MainCategoryAdapter(var mainCategoryList:ArrayList<CategoryItems>): RecyclerView.Adapter<MainCategoryAdapter.CategoryHolder>() {
    private lateinit var binding: ItemRvMainCategoryBinding
    private lateinit var inflater: LayoutInflater
    private var context:Context? = null
    var listener : OnClickCategoryItem ? = null

    fun  setClickListener(liste : OnClickCategoryItem){
        listener = liste
    }
    inner class CategoryHolder(b: ItemRvMainCategoryBinding): RecyclerView.ViewHolder(b.root){
        fun bind(recipe: CategoryItems) {
           binding.tvDishName.text = recipe.strCategory
            Glide.with(context!!).load(recipe.strCategoryThumb).into(binding.imgDish)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        inflater = LayoutInflater.from(parent.context)
        context = parent.context
        binding= ItemRvMainCategoryBinding.inflate(inflater,parent,false)
        return CategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(mainCategoryList[position])
        holder.itemView.setOnClickListener {
            listener!!.onClickItem(mainCategoryList[position].strCategory)
        }
    }

    override fun getItemCount(): Int {
     return  mainCategoryList.size
    }
    interface OnClickCategoryItem{
        fun onClickItem(categoryname:String)
    }
}