package com.example.kotlin.ui

import androidx.lifecycle.ViewModel
import com.example.kotlin.data.Quote
import com.example.kotlin.data.QuoteRepository

// QuoteRepository dependency will again be passed in the
// constructor using dependency injection
class MainViewModel(private val quoteRepository: QuoteRepository)
    : ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)
}