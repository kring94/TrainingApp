package com.example.calculator.quotesapp.domain

import com.example.calculator.quotesapp.data.QuoteRepository
import com.example.calculator.quotesapp.data.database.entities.toDatabase
import com.example.calculator.quotesapp.domain.model.Quote
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest{
    /*Manual tests*/
    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    @Test
    fun `when the api does not return anything then get values from database`() = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()
        //When
        getQuotesUseCase()
        //then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
    }

    @Test
    fun `when the api return anything then get values from api`() = runBlocking {
        //Given
        val myList = listOf(Quote("Quote 1", "Author 1"))
        coEvery { quoteRepository.getAllQuotesFromApi() } returns myList
        //When
        val response = getQuotesUseCase()
        //then
        coVerify(exactly = 1) { quoteRepository.clearQuotes()}
        coVerify(exactly = 1) { quoteRepository.insertQuotes(any()) }
        coVerify(exactly = 0) {quoteRepository.getAllQuotesFromDatabase()}
        assert(myList == response)
    }


    /*ChatGPT tests*/
/*    private lateinit var repository: QuoteRepository
    private lateinit var useCase: GetQuotesUseCase

    @Before
    fun setup() {
        repository = mockk<QuoteRepository>()
        useCase = GetQuotesUseCase(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `invoke should return quotes from API when they are not empty`() = runBlocking {
        // Given
        val quotesFromApi = listOf(
            Quote("quote 1", "author 1"),
            Quote("quote 2", "author 2")
        )
        coEvery { repository.getAllQuotesFromApi() } returns quotesFromApi
        coEvery { repository.clearQuotes() } just runs
        coEvery { repository.insertQuotes(any()) } just runs

        // When
        val result = useCase.invoke()

        // Then
        coVerify { repository.getAllQuotesFromApi() }
        coVerify { repository.clearQuotes() }
        coVerify {
            repository.insertQuotes(
                quotesFromApi.map { it.toDatabase() }
            )
        }
        coVerify(exactly = 0) { repository.getAllQuotesFromDatabase() }
        assert(result == quotesFromApi)
    }

    @Test
    fun `invoke should return quotes from database when they are empty`() = runBlocking {
        // Given
        coEvery { repository.getAllQuotesFromApi() } returns emptyList()
        val quotesFromDatabase = listOf(
            Quote("quote 1", "author 1"),
            Quote("quote 2", "author 2")
        )
        coEvery { repository.getAllQuotesFromDatabase() } returns quotesFromDatabase

        // When
        val result = useCase.invoke()

        // Then
        coVerify { repository.getAllQuotesFromApi() }
        coVerify(exactly = 0) { repository.clearQuotes() }
        coVerify(exactly = 0) { repository.insertQuotes(any()) }
        coVerify { repository.getAllQuotesFromDatabase() }
        assert(result == quotesFromDatabase)
    }*/
}
