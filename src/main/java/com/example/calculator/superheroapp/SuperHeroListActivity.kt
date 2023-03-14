package com.example.calculator.superheroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent.DispatcherState
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculator.R
import com.example.calculator.databinding.ActivitySuperHeroListBinding
import com.example.calculator.superheroapp.DetailSuperheroActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
const val TAG_RETROFIT = "Retrofit response"

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroListBinding
    //Retrofit
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })

        adapter = SuperheroAdapter {navigateToDetail(it)}
        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperhero.adapter = adapter

    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroDataResponse> = retrofit.create(ApiService::class.java).getSuperheros(query)
            if(myResponse.isSuccessful){
                Log.d(  TAG_RETROFIT, "Working")
                val response: SuperHeroDataResponse? = myResponse.body()
                if(response != null){
                    Log.d(  TAG_RETROFIT, response.superheros.toString())
                    runOnUiThread {
                        adapter.updateList(response.superheros)
                        binding.progressBar.isVisible = false
                    }
                }
            }else{

                Log.d(  TAG_RETROFIT, "Don't working")
            }
        }
    }

    //Retrofit
    private fun getRetrofit():Retrofit{
        return  Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id:String){
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}