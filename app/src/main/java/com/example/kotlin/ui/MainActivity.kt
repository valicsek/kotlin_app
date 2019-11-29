package com.example.kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlin.R
import com.example.kotlin.data.model.Quote
import com.example.kotlin.ui.adapter.QuoteAdapter
import com.example.kotlin.utilities.InjectorUtils

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var quoteAdapter : QuoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUserInterface()
    }

    private fun initializeUserInterface() {
        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        val viewModel = ViewModelProviders.of(this, factory)
            .get(MainViewModel::class.java)

        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        viewModel.getQuotes().observe(this, Observer { quotes ->
            // An adapter loads the information to be displayed from a data source,
            // such as an array or database query, and creates a view for each item.
            // Then it inserts the views into the ListView.
            // quoteAdapter = ArrayAdapter(this, R.layout.list_view, quotes)
            quoteAdapter = QuoteAdapter(this, R.layout.support_simple_spinner_dropdown_item, quotes)
            quote_list_view.adapter = quoteAdapter
            textView_items_left.text = quotes.size.toString() + " Quotes"
        })

        setListeners(viewModel)
    }

    private fun setListeners(viewModel : MainViewModel) {
        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
        button_add_quote.setOnClickListener {
            val quote = Quote(
                editText_quote.text.toString(),
                editText_author.text.toString()
            )

            if (editText_author.text.isNotEmpty() && editText_quote.text.isNotEmpty()) {
                viewModel.addQuote(quote)
                editText_quote.setText("")
                editText_author.setText("")
            }
        }

        // Open by default the search view.
        searchView_quote.setIconifiedByDefault(false)
        // When search bar change, filter the data based on it.
        searchView_quote.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                quoteAdapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                return false
            }

        })
    }
}
