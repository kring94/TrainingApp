package com.example.calculator.quotesapp.data.network

import com.example.calculator.quotesapp.core.RetrofitHelper
import com.example.calculator.quotesapp.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class QuoteService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuote(): List<QuoteModel>{
        return withContext(Dispatchers.IO) {
            val response: Response<List<QuoteModel>> = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}