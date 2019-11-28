package com.example.kotlin.utilities

import com.example.kotlin.data.FakeDatabase
import com.example.kotlin.data.QuoteRepository
import com.example.kotlin.ui.MainViewModelFactory

object InjectorUtils {
    // This will be called from QuotesActivity
    fun provideQuotesViewModelFactory(): MainViewModelFactory {
        // ViewModelFactory needs a repository, which in turn needs a DAO from a database
        // The whole dependency tree is constructed right here, in one place
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return MainViewModelFactory(quoteRepository)
    }
}