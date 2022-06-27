package com.virus.recipeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.virus.recipeapp.adapter.MainCategoryAdapter
import com.virus.recipeapp.adapter.SubCategoryAdapter
import com.virus.recipeapp.database.RecipeDatabase
import com.virus.recipeapp.databinding.ActivityHomeBinding
import com.virus.recipeapp.entity.CategoryItems
import com.virus.recipeapp.entity.MealItems
import com.virus.recipeapp.entity.Recipe
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity(),SubCategoryAdapter.OnClickCategoryItem {
    private lateinit var binding: ActivityHomeBinding
    var subCategoryList = ArrayList<MealItems>()
    var mainCategoryList = ArrayList<CategoryItems>()
    private lateinit var subCategoryAdapter: SubCategoryAdapter
    private lateinit var mainCategoryAdapter: MainCategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getCategoriesFromRoom()




    }
    private val onclicked = object : MainCategoryAdapter.OnClickCategoryItem{
        override fun onClickItem(categoryname: String) {
            getMealsFromRoom(categoryname)        }

    }
//    private val onclickMeal = object : SubCategoryAdapter.OnClickCategoryItem{
//        override fun onClickItem(id: String) {
//            var intent = Intent(this@HomeActivity,DetailActivity::class.java)
//            intent.putExtra("id",id)
//            startActivity(intent)
//        }
//
//    }

    private fun getCategoriesFromRoom() {
        launch {
            this.let {
                var cat =
                    RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().allCategoryItems()
                mainCategoryList = cat as ArrayList<CategoryItems>
                mainCategoryList.reverse()
                getMealsFromRoom(mainCategoryList[0].strCategory)
                mainCategoryAdapter = MainCategoryAdapter(mainCategoryList)
                mainCategoryAdapter.setClickListener(onclicked)

                binding.rvMainCategory.layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                binding.rvMainCategory.adapter = mainCategoryAdapter
            }
        }
    }
    private fun getMealsFromRoom(categoryName: String) {
        binding.tvCategory.text = "$categoryName category"
        launch {
            this.let {
                var cat =
                    RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getMealsBySpecificCategory(categoryName)
                subCategoryList = cat as ArrayList<MealItems>
                Log.e( "getMealsFromRoom: ", cat.toString())
                subCategoryAdapter = SubCategoryAdapter(subCategoryList)
                binding.rvSubCategory.layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                binding.rvSubCategory.adapter = subCategoryAdapter
                subCategoryAdapter.setClickListener(this@HomeActivity)
            }
        }
    }

    override fun onClickItem(id: String) {
        var intent = Intent(this@HomeActivity,DetailActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }
}