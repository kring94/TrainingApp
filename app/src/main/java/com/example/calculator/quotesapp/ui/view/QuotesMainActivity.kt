package com.example.calculator.quotesapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.calculator.databinding.ActivityQuotesMainBinding
import com.example.calculator.quotesapp.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuotesMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuotesMainBinding

    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        quoteViewModel.quoteModel.observe(this) { currentQuote ->
            binding.apply {
                tvQuote.text = currentQuote.quote
                tvAuthor.text = currentQuote.author
            }
        }

        quoteViewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }

        binding.viewContainer.setOnClickListener{
            quoteViewModel.randomQuote()
        }

    }
}