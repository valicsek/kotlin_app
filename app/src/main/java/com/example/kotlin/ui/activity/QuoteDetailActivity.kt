package com.example.kotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin.R
import kotlinx.android.synthetic.main.activity_quote_detail.*

class QuoteDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_detail)

        setupUserInterface()
    }

    private fun setupUserInterface() {
        title = "Quote Details Activity"

        // Set the data from the MainActivity
        val author = intent.getStringExtra("author")
        val quote = intent.getStringExtra("quote")

        textView_author.text = author
        textView_quote.text = quote
    }
}
