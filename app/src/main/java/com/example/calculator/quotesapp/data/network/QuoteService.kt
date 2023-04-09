package com.example.calculator.quotesapp.data.network

import com.example.calculator.quotesapp.core.RetrofitHelper
import com.example.calculator.quotesapp.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class QuoteService @Inject constructor(
    private val api:QuoteApiClient
){

    suspend fun getQuote(): List<QuoteModel>{
        return withContext(Dispatchers.IO) {
            val response: Response<List<QuoteModel>> = api.getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}