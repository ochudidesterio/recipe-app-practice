package com.virus.recipeapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.virus.recipeapp.api.ApiService
import com.virus.recipeapp.databinding.ActivityDetailBinding
import com.virus.recipeapp.entity.Meal
import com.virus.recipeapp.entity.MealResponse
import com.virus.recipeapp.retrofit.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    var youtubeLink = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var id = intent.getStringExtra("id")
        if (id != null) {
            Log.e( "onCreate: ", id)
            getSpecificMeal(id)

        }
        binding.imgToolbarBtnBack.setOnClickListener {
            finish()
        }
        binding.btnYoutube.setOnClickListener {
            val uri = Uri.parse(youtubeLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun getSpecificMeal(id: String){
        val apiService = RetrofitClientInstance.retrofitClientInstance!!.create(ApiService::class.java)
        val callback = apiService.getMealDetail(id)
        callback.enqueue(object : Callback<MealResponse> {
            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {
                Glide.with(this@DetailActivity).load(response.body()!!.meals[0].strmealthumb).into(binding.imgItem)
                Log.e("onResponse: ", response.body()!!.meals[0].strmeal)
                binding.tvCategory.text = response.body()!!.meals[0].strmeal

                var ingredient = "${response.body()!!.meals[0].stringredient1}      ${response.body()!!.meals[0].strmeasure1}\n" +
                        "${response.body()!!.meals[0].stringredient2}      ${response.body()!!.meals[0].strmeasure2}\n" +
                        "${response.body()!!.meals[0].stringredient3}      ${response.body()!!.meals[0].strmeasure3}\n" +
                        "${response.body()!!.meals[0].stringredient4}      ${response.body()!!.meals[0].strmeasure4}\n" +
                        "${response.body()!!.meals[0].stringredient5}      ${response.body()!!.meals[0].strmeasure5}\n" +
                        "${response.body()!!.meals[0].stringredient6}      ${response.body()!!.meals[0].strmeasure6}\n" +
                        "${response.body()!!.meals[0].stringredient7}      ${response.body()!!.meals[0].strmeasure7}\n" +
                        "${response.body()!!.meals[0].stringredient8}      ${response.body()!!.meals[0].strmeasure8}\n" +
                        "${response.body()!!.meals[0].stringredient9}      ${response.body()!!.meals[0].strmeasure9}\n" +
                        "${response.body()!!.meals[0].stringredient10}      ${response.body()!!.meals[0].strmeasure10}\n" +
                        "${response.body()!!.meals[0].stringredient11}      ${response.body()!!.meals[0].strmeasure11}\n" +
                        "${response.body()!!.meals[0].stringredient12}      ${response.body()!!.meals[0].strmeasure12}\n" +
                        "${response.body()!!.meals[0].stringredient13}      ${response.body()!!.meals[0].strmeasure13}\n" +
                        "${response.body()!!.meals[0].stringredient14}      ${response.body()!!.meals[0].strmeasure14}\n" +
                        "${response.body()!!.meals[0].stringredient15}      ${response.body()!!.meals[0].strmeasure15}\n" +
                        "${response.body()!!.meals[0].stringredient16}      ${response.body()!!.meals[0].strmeasure16}\n" +
                        "${response.body()!!.meals[0].stringredient17}      ${response.body()!!.meals[0].strmeasure17}\n" +
                        "${response.body()!!.meals[0].stringredient18}      ${response.body()!!.meals[0].strmeasure18}\n" +
                        "${response.body()!!.meals[0].stringredient19}      ${response.body()!!.meals[0].strmeasure19}\n" +
                        "${response.body()!!.meals[0].stringredient20}      ${response.body()!!.meals[0].strmeasure20}\n"

                binding.tvIngredients.text = ingredient
                binding.tvInstructions.text = response.body()!!.meals[0].strinstructions

                if (response.body()!!.meals[0].strsource != null){
                    youtubeLink = response.body()!!.meals[0].strsource
                }else{
                    binding.btnYoutube.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Log.e( "onFailure: ", t.message.toString())
                Toast.makeText(this@DetailActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}