package com.example.calculator.quotesapp.data

import com.example.calculator.quotesapp.data.model.QuoteModel
import com.example.calculator.quotesapp.data.model.QuoteProvider
import com.example.calculator.quotesapp.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api : QuoteService,
    private val quoteProvider: QuoteProvider
) {

    suspend fun getAllQuotes(): List<QuoteModel> {
        val response: List<QuoteModel> = api.getQuote()
        quoteProvider.quotes = response
        return response
    }
}