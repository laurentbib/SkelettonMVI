package com.biblublab.domain.feature.home

import java.util.*

data class News(
    val title: String,
    val description: String,
    val url: String,
    val urlImage: String,
    val publishedDate: Date?
)
