package com.example.kotlin.ui.adapter

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import com.example.kotlin.data.model.Quote


class QuoteAdapter(
    context: Context,
    textViewResourceId: Int,
    quoteList: List<Quote>
) : ArrayAdapter<Quote>(context, textViewResourceId, quoteList) {

    private var quoteList: List<Quote> = quoteList
    private var filteredQuoteList: List<Quote> = quoteList

    /*
    init {
        this.quoteList = quoteList
    }
    */


    override fun getCount(): Int {
        return filteredQuoteList.size
    }

    override fun getItem(position: Int): Quote? {
        return filteredQuoteList[position]
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.simple_list_item_1, null)

        view.findViewById<TextView>(R.id.text1).text = filteredQuoteList[position].author + " : " + filteredQuoteList[position].quote


        // return super.getView(position, convertView, parent)
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                filteredQuoteList = filterResults.values as List<Quote>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    quoteList
                else
                    quoteList.filter {
                        it.author.toLowerCase().contains(queryString) ||
                                it.quote.toLowerCase().contains(queryString)
                    }
                return filterResults
            }
        }
    }
        // return super.getFilter()
}
