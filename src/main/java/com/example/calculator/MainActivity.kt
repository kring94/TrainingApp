package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.calculator.imcapp.ImcActivity
import com.example.calculator.superheroapp.SuperHeroListActivity
import com.example.calculator.todoapp.TodoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imcButton = findViewById<Button>(R.id.imc_button)
        val todoButton = findViewById<Button>(R.id.TODO_button)
        val superheroButton = findViewById<Button>(R.id.super_hero_button)

        imcButton.setOnClickListener{navigateToImcApp()}
        todoButton.setOnClickListener { navigateToTodoApp() }
        superheroButton.setOnClickListener { navigateToSuperheroApp() }
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
}