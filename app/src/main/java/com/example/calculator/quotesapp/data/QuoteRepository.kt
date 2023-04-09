package com.example.calculator.quotesapp.data

import com.example.calculator.quotesapp.data.model.QuoteModel
import com.example.calculator.quotesapp.data.model.QuoteProvider
import com.example.calculator.quotesapp.data.network.QuoteService

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes(): List<QuoteModel> {
        val response: List<QuoteModel> = api.getQuote()
        QuoteProvider.quotes = response
        return response
    }
}