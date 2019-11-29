package com.example.kotlin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "quote") val quote: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id") var id: Int = 0
}
