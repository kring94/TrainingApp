package com.example.calculator.settingsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.R
import com.example.calculator.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}