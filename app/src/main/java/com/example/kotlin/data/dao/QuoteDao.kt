package com.example.kotlin.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlin.data.model.Quote

@Dao
interface QuoteDao {

    @Query("SELECT * from Quote")
    fun getQuotes(): LiveData<List<Quote>>

    @Query("SELECT * FROM Quote WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Quote>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg quotes: Quote)

    @Delete
    suspend fun delete(quote: Quote)
}