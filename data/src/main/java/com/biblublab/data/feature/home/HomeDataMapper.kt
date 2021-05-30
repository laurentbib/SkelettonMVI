package com.biblublab.data.feature.home

import com.biblublab.data.common.EMPTY_STRING
import com.biblublab.data.common.toDate
import com.biblublab.data.repository.NewsDto
import com.biblublab.domain.feature.home.News
import javax.inject.Inject

class HomeDataMapper @Inject constructor() {

    fun toNewsList(articleResponse: ArticleResponse) : List<NewsDto> = articleResponse.articles.map { toNewsDto(it) }

    private fun toNewsDto(newsResponse: NewsResponse) : NewsDto {
        return with(newsResponse){
            NewsDto(title = title,
                description = description ?: EMPTY_STRING,
                url = url ?: EMPTY_STRING,
                urlImage = urlToImage ?: EMPTY_STRING,
                timeStamp = publishedAt
            )
        }
    }

    fun toNewsFromDBList(allNews : List<NewsDto>) : List<News> = allNews.map { toNewsFromDb(it) }

    private fun toNewsFromDb(newsDto : NewsDto) : News {
        return with(newsDto){
            News(title, description, url, urlImage, timeStamp.toDate() )
        }
    }
}