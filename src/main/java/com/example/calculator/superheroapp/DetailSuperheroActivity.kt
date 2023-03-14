package com.example.calculator.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.example.calculator.R
import com.example.calculator.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class DetailSuperheroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "superhero_id"
    }

    private lateinit var binding: ActivityDetailSuperheroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetail = getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)
            if(superheroDetail.body() != null){
                runOnUiThread{
                    createUI(superheroDetail.body()!!)
                }
            }
        }
    }

    private fun createUI(superhero: SuperheroResponseDetail) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
    }


    //Retrofit
    private fun getRetrofit(): Retrofit {
        return  Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}