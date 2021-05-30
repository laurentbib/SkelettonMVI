package com.biblublab.data.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsDto(
    val title: String,
    val description: String,
    val url: String,
    val urlImage: String,
    val timeStamp: String
) {
    @PrimaryKey
    var id: String = "$title - $timeStamp"

}