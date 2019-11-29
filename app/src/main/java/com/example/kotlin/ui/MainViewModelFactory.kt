package com.example.kotlin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.data.repository.QuoteRepository

// The same repository that's needed for QuotesViewModel
// is also passed to the factory
class MainViewModelFactory(private val quoteRepository: QuoteRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(quoteRepository) as T
    }
}