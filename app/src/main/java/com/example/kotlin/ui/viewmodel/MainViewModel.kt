package com.example.kotlin.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin.data.AppDatabase
import com.example.kotlin.data.model.Quote
import com.example.kotlin.data.repository.QuoteRepository
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class MainViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: QuoteRepository
    // LiveData gives us updated quotes when they change.
    val allQuotes: LiveData<List<Quote>>

    init {
        val wordsDao = AppDatabase.getDatabase(application, viewModelScope).quoteDao()
        repository = QuoteRepository.getInstance(wordsDao)
        // Request all of the quotes
        allQuotes = repository.getQuotes()
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(quote: Quote) = viewModelScope.launch {
        repository.addQuote(quote)
    }

    fun delete(quote: Quote) = viewModelScope.launch {
        repository.deleteQuote(quote)
    }
}