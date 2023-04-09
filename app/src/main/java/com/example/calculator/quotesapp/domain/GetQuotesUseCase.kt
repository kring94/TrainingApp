package com.example.calculator.quotesapp.domain

import com.example.calculator.quotesapp.data.QuoteRepository
import com.example.calculator.quotesapp.data.model.QuoteModel
import javax.inject.Inject


class GetQuotesUseCase @Inject constructor(
    private val repository : QuoteRepository
){

    suspend operator fun invoke(): List<QuoteModel>? = repository.getAllQuotes()

}