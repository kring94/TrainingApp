package com.example.calculator.quotesapp.domain

import com.example.calculator.quotesapp.data.model.QuoteModel
import com.example.calculator.quotesapp.data.model.QuoteProvider

class GetRandomQuoteUseCase {

    operator fun invoke(): QuoteModel?{
        val quotes = QuoteProvider.quotes
        if(!quotes.isNullOrEmpty()){
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }
}