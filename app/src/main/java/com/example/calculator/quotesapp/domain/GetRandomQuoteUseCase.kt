package com.example.calculator.quotesapp.domain

import com.example.calculator.quotesapp.data.QuoteRepository
import com.example.calculator.quotesapp.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
){

    suspend operator fun invoke(): Quote?{
        val quotes = repository.getAllQuotesFromDatabase()
        if(!quotes.isNullOrEmpty()){
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }
}