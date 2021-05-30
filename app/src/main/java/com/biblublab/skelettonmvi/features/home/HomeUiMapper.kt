package com.biblublab.skelettonmvi.features.home

import com.biblublab.data.common.EMPTY_STRING
import com.biblublab.domain.feature.home.News
import com.biblublab.skelettonmvi.common.toFormattedDate
import javax.inject.Inject

class HomeUiMapper @Inject constructor() {

    fun toNewsUi(news : News) : NewsUi{
      return  with(news){
            NewsUi(title = title,
            description = description,
            url = url,
            urlImage = urlImage,
            formattedDate = publishedDate?.let { it.toFormattedDate() }?: EMPTY_STRING)
        }
    }

}