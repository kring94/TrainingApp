package com.example.calculator.quotesapp.domain

import com.example.calculator.quotesapp.data.QuoteRepository
import com.example.calculator.quotesapp.data.database.entities.toDatabase
import com.example.calculator.quotesapp.domain.model.Quote
import javax.inject.Inject


class GetQuotesUseCase @Inject constructor(
    private val repository : QuoteRepository
){

    suspend operator fun invoke(): List<Quote>{
        val quotes = repository.getAllQuotesFromApi()

        return if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }
    }

}