package com.example.calculator.quotesapp.domain.model

import com.example.calculator.quotesapp.data.database.entities.QuoteEntity
import com.example.calculator.quotesapp.data.model.QuoteModel

data class Quote(
    val quote: String,
    val author: String
)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote,author)