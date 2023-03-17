package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.imcapp.ImcActivity
import com.example.calculator.settingsapp.SettingsActivity
import com.example.calculator.superheroapp.SuperHeroListActivity
import com.example.calculator.todoapp.TodoActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            imcButton.setOnClickListener { navigateToImcApp() }
            TODOButton.setOnClickListener { navigateToTodoApp() }
            superHeroButton.setOnClickListener { navigateToSuperheroApp() }
            settingsButton.setOnClickListener { navigateToSettingsApp() }
        }

    }

    private fun navigateToSuperheroApp() {
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToImcApp(){
        val intent = Intent(this, ImcActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToTodoApp(){
        val intent = Intent(this,TodoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSettingsApp(){
        val intent = Intent(this,SettingsActivity::class.java)
        startActivity(intent)
    }
}