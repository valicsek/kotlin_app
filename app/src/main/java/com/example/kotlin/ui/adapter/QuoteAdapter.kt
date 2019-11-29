package com.example.kotlin.ui.adapter

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.kotlin.data.model.Quote


class QuoteAdapter(
    context: Context,
    textViewResourceId: Int,
    quoteList: List<Quote>
) : ArrayAdapter<Quote>(context, textViewResourceId, quoteList) {

    private var quoteList: List<Quote> = quoteList

    /*
    init {
        this.quoteList = quoteList
    }
    */

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.simple_list_item_1, null)

        view.findViewById<TextView>(R.id.text1).text = quoteList[position].author + " : " + quoteList[position].quoteText


        // return super.getView(position, convertView, parent)
        return view
    }
}