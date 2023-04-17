package com.example.calculator.quotesapp.domain

import com.example.calculator.quotesapp.data.QuoteRepository
import com.example.calculator.quotesapp.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest {
    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }


    @Test
    fun  `when the database contain something return random quote from database` () = runBlocking {
        //Given
        val myRandomList = listOf(Quote("Quote 1", "Author 1"))
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns myRandomList
        //When
        val response = getRandomQuoteUseCase()
        //Then
        assert(myRandomList[0] == response)

    }

    @Test
    fun  `when the database does not contain something return null` () = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns emptyList()
        //When
        val response = getRandomQuoteUseCase()
        //Then
        assertNull(response)

    }


}

/*class GetRandomQuoteUseCaseTest {

    private lateinit var useCase: GetRandomQuoteUseCase
    private lateinit var repository: QuoteRepository

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetRandomQuoteUseCase(repository)
    }

    @Test
    fun `invoke should return a random quote when quotes exist in database`() = runBlocking {
        // Given
        val quotesFromDatabase = listOf(
            Quote("quote 1", "author 1"),
            Quote("quote 2", "author 2"),
            Quote("quote 3", "author 3")
        )
        coEvery { repository.getAllQuotesFromDatabase() } returns quotesFromDatabase

        // When
        val result = useCase()

        // Then
        coVerify { repository.getAllQuotesFromDatabase() }
        assertNotNull(result)
        assertTrue(result in quotesFromDatabase)
    }

    @Test
    fun `invoke should return null when quotes do not exist in database`() = runBlocking {
        // Given
        coEvery { repository.getAllQuotesFromDatabase() } returns emptyList()

        // When
        val result = useCase()

        // Then
        coVerify { repository.getAllQuotesFromDatabase() }
        assertNull(result)
    }
}*/

