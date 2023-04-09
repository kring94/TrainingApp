package com.example.calculator.quotesapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.calculator.databinding.ActivityQuotesMainBinding
import com.example.calculator.quotesapp.viewmodel.QuoteViewModel

class QuotesMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuotesMainBinding

    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.quoteModel.observe(this, Observer {currentQuote ->
            binding.apply {
                tvQuote.text = currentQuote.quote
                tvAuthor.text = currentQuote.author
            }

        })
        binding.viewContainer.setOnClickListener{
            quoteViewModel.randomQuote()
        }

    }
}