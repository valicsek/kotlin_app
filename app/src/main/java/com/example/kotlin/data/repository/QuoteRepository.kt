package com.example.kotlin.data.repository

// import com.example.kotlin.data.FakeQuoteDao
import com.example.kotlin.data.dao.QuoteDao
import com.example.kotlin.data.model.Quote

// FakeQuoteDao must be passed in - it is a dependency
// You could also instantiate the DAO right inside the class without all the fuss, right?
// No. This would break testability - you need to be able to pass a mock version of a DAO
// to the repository (e.g. one that upon calling getQuotes() returns a dummy list of quotes for testing)
// This is the core idea behind DEPENDENCY INJECTION - making things completely modular and independent.
class QuoteRepository private constructor(private val quoteDao: QuoteDao) {

    suspend fun addQuote(quote: Quote) {
        quoteDao.insertAll(quote)
    }

    fun getQuotes() = quoteDao.getQuotes()

    suspend fun deleteQuote(quote: Quote) {
        quoteDao.delete(quote)
    }

    companion object {
        // Singleton instantiation we already know and love
        @Volatile private var instance: QuoteRepository? = null

        fun getInstance(quoteDao: QuoteDao) =
            instance ?: synchronized(this) {
                instance
                    ?: QuoteRepository(quoteDao).also { instance = it }
            }
    }
}