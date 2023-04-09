package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.busscheduleapp.BusMainActivity
import com.example.calculator.cukcakeapp.CupcakeMainActivity
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.horoscopeapp.ui.HoroscopeMainActivity
import com.example.calculator.imcapp.ImcActivity
import com.example.calculator.inventoryapp.InventoryMainActivity
import com.example.calculator.quotesapp.view.QuotesMainActivity
import com.example.calculator.settingsapp.SettingsActivity
import com.example.calculator.superheroapp.SuperHeroListActivity
import com.example.calculator.todoapp.TodoActivity
import com.example.calculator.unscrambleapp.UnscrambleMainActivity
import com.example.calculator.wordsapp.WordsMainActivity

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
            navFragmentsButton.setOnClickListener { navigateToWordsApp() }
            MVVMButton.setOnClickListener { navigateToUnscrambleApp() }
            advNavFragmentsButton.setOnClickListener { navigateToCupcakeApp() }
            roomButton.setOnClickListener { navigateToBusApp() }
            advRoomButton.setOnClickListener { navigateToInventoryApp() }
            cleanAchAppButton.setOnClickListener { navigateToHoroscopeApp() }
            quoteButton.setOnClickListener { navigateToQuoteApp() }

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

    private fun navigateToWordsApp(){
        val intent = Intent(this, WordsMainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToUnscrambleApp(){
        val intent = Intent(this, UnscrambleMainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToCupcakeApp(){
        val intent = Intent(this, CupcakeMainActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToBusApp(){
        val intent = Intent(this, BusMainActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToInventoryApp(){
        val intent = Intent(this, InventoryMainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToHoroscopeApp(){
        val intent = Intent(this, HoroscopeMainActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToQuoteApp(){
        val intent = Intent(this, QuotesMainActivity::class.java)
        startActivity(intent)
    }
}