package com.example.calculator.quotesapp.data

import com.example.calculator.quotesapp.data.database.dao.QuoteDao
import com.example.calculator.quotesapp.data.database.entities.QuoteEntity
import com.example.calculator.quotesapp.data.network.QuoteService
import com.example.calculator.quotesapp.domain.model.Quote
import com.example.calculator.quotesapp.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api : QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response = api.getQuote()
        return response.map { it.toDomain() }
    }
    suspend fun getAllQuotesFromDatabase():List<Quote>{
        val response = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }
    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }

}