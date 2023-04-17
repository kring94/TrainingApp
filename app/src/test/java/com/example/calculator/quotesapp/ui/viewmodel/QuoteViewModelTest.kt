package com.example.calculator.quotesapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.calculator.quotesapp.domain.GetQuotesUseCase
import com.example.calculator.quotesapp.domain.GetRandomQuoteUseCase
import com.example.calculator.quotesapp.domain.model.Quote
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class QuoteViewModelTest{
    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase
    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase,getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel is created at the first time, get all quotes and set the first value`()= runTest {
        //Given
        val quoteList = listOf(
            Quote("quote 1", "author 1"),
            Quote("quote 2", "author 2")
        )
        coEvery { getQuotesUseCase() } returns quoteList
        //When
        quoteViewModel.onCreated()
        //Then
        assert(quoteViewModel.quoteModel.value == quoteList.first())
    }

    @Test
    fun `when randomQuoteUseCase return a quote set on the livedata`()= runTest {
        //Given
        val quote =  Quote("quote 1", "author 1")

        coEvery { getRandomQuoteUseCase() } returns quote
        //When
        quoteViewModel.randomQuote()
        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }
    @Test
    fun `if randomQuoteUseCase return null keep the last value`()= runTest {
        //Given
        val quote =  Quote("quote 1", "author 1")
        quoteViewModel.quoteModel.value = quote
        coEvery { getRandomQuoteUseCase() } returns null
        //When
        quoteViewModel.randomQuote()
        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }


}

/*@ExperimentalCoroutinesApi
class QuoteViewModelTest{
    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase
    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase,getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    // Test para verificar que la función onCreated actualiza el valor de quoteModel
    @Test
    fun `onCreated should update quoteModel value`() {
        // Se define el valor esperado para el caso de uso GetQuotesUseCase
        val expectedQuoteList = listOf(Quote("Test quote", "Test author"))

        // Se utiliza la función every de Mockk para definir el comportamiento esperado
        coEvery { getQuotesUseCase() } returns expectedQuoteList

        // Se llama a la función onCreated del viewModel
        quoteViewModel.onCreated()

        // Se verifica que el valor de quoteModel se haya actualizado correctamente
        assertEquals(expectedQuoteList[0], quoteViewModel.quoteModel.value)
    }

    // Test para verificar que la función randomQuote actualiza el valor de quoteModel
    @Test
    fun `randomQuote should update quoteModel value`() {
        // Se define el valor esperado para el caso de uso GetRandomQuoteUseCase
        val expectedQuote = Quote("Test quote", "Test author")

        // Se utiliza la función coEvery de Mockk para definir el comportamiento esperado
        coEvery { getRandomQuoteUseCase() } returns expectedQuote

        // Se llama a la función randomQuote del viewModel
        quoteViewModel.randomQuote()

        // Se verifica que el valor de quoteModel se haya actualizado correctamente
        assertEquals(expectedQuote, quoteViewModel.quoteModel.value)
    }

    // Test para verificar que la función randomQuote actualiza el valor de isLoading
    @Test
    fun `randomQuote should update isLoading value`() {
        // Se utiliza la función coEvery de Mockk para definir el comportamiento esperado
        coEvery { getRandomQuoteUseCase() } coAnswers { delay(500); Quote("", "") }

        // Se llama a la función randomQuote del viewModel
        quoteViewModel.randomQuote()

        // Se verifica que el valor de isLoading se haya actualizado correctamente
        assertEquals(true, quoteViewModel.isLoading.value)

        // Se espera un tiempo suficiente para que la función termine de ejecutarse
        Thread.sleep(1000)

        // Se verifica que el valor de isLoading se haya actualizado correctamente
        assertEquals(false, quoteViewModel.isLoading.value)
    }


}*/







