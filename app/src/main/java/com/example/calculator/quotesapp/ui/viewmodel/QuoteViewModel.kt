package com.example.calculator.quotesapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.quotesapp.data.model.QuoteModel
import com.example.calculator.quotesapp.domain.GetQuotesUseCase
import com.example.calculator.quotesapp.domain.GetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuoteUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
): ViewModel() {

    val quoteModel = MutableLiveData<QuoteModel>()
    val isLoading = MutableLiveData<Boolean>()


    fun randomQuote(){
        isLoading.postValue(true)
        val quote = getRandomQuoteUseCase()
        if (quote != null){
            quoteModel.value = quote
        }
        isLoading.postValue(false)
    }

    init {
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