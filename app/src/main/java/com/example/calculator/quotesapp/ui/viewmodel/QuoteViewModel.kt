package com.example.calculator.quotesapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.quotesapp.domain.GetQuotesUseCase
import com.example.calculator.quotesapp.domain.GetRandomQuoteUseCase
import com.example.calculator.quotesapp.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuoteUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
): ViewModel() {

    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()


    fun randomQuote(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote = getRandomQuoteUseCase()
            /*if (quote != null){
                quoteModel.value = quote
            }*/
            quote?.let { quoteModel.value = it }
            isLoading.postValue(false)
        }
    }

    fun onCreated() {
        randomQuote()
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuoteUseCase()

            if(!result.isNullOrEmpty()){
                quoteModel.value = result[0]
                isLoading.value = false
            }
        }
    }
}