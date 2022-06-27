package com.virus.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.virus.recipeapp.api.ApiService
import com.virus.recipeapp.database.RecipeDatabase
import com.virus.recipeapp.databinding.ActivitySplashBinding
import com.virus.recipeapp.entity.Categories
import com.virus.recipeapp.entity.Meal
import com.virus.recipeapp.entity.MealItems
import com.virus.recipeapp.retrofit.RetrofitClientInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.jar.Manifest

class SplashActivity : BaseActivity(),EasyPermissions.PermissionCallbacks,EasyPermissions.RationaleCallbacks {
    private lateinit var binding: ActivitySplashBinding
    private var READ_STORAGE_PERMISSION =123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readStorageTask()
        binding.btnStarted.setOnClickListener {
            var intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }
    fun getCategories(){
        val apiService = RetrofitClientInstance.retrofitClientInstance!!.create(ApiService::class.java)
        val callback = apiService.getAllCategories()
        callback.enqueue(object : Callback<Categories>{
            override fun onResponse(
                call: Call<Categories>,
                response: Response<Categories>
            ) {
                for (arr in response.body()!!.categories!!){
                    getMeals(arr.strCategory)
                }
                insertCategoriesToRoom(response.body())
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                binding.loader.visibility = View.INVISIBLE
                Toast.makeText(this@SplashActivity,"Something went wrong",Toast.LENGTH_LONG).show()
            }
        })
    }
    fun getMeals(categoryName:String){
        val apiService = RetrofitClientInstance.retrofitClientInstance!!.create(ApiService::class.java)
        val callback = apiService.getMeals(categoryName)
        callback.enqueue(object : Callback<Meal>{
            override fun onResponse(
                call: Call<Meal>,
                response: Response<Meal>
            ) {
                insertMealsToRoom(categoryName,response.body())
            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                Toast.makeText(this@SplashActivity,"Something went wrong",Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun hasReadStoragePermission():Boolean{
        return EasyPermissions.hasPermissions(this@SplashActivity,android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }
    private fun readStorageTask(){
        if(hasReadStoragePermission()){
            clearDb()
            getCategories()
        }else{
            EasyPermissions.requestPermissions(this,
                "This app needs access to your storage",
                READ_STORAGE_PERMISSION,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
    fun insertCategoriesToRoom(categories: Categories?){
        launch {
            this.let {
                for (arr in categories!!.categories!!){
                    RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().insertCategoryItem(arr)
                }
            }
        }

    }
    fun insertMealsToRoom(categoryName: String,meal: Meal?){
        launch {
            this.let {
                for (arr in meal!!.meals!!){
                    var mealItemModel = MealItems(
                        arr.id,
                        arr.idMeal,
                        categoryName,
                        arr.strMeal,
                        arr.strMealThumb
                    )
                    RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().insertMealItem(mealItemModel)
                    Log.e( "insertMealsToRoom: ",arr.toString() )

                }
                binding.btnStarted.visibility = View.VISIBLE
            }
        }

    }
    fun clearDb(){
        launch {
            this.let {
                RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().clearDb()
            }
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onRationaleAccepted(requestCode: Int) {
    }

    override fun onRationaleDenied(requestCode: Int) {
    }
}