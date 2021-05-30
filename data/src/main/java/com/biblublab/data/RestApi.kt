package com.biblublab.data

import com.biblublab.data.feature.home.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestApi {

    @GET("top-headlines?country=fr&apiKey=67d7cfa5dba943539bb5b613b636eebc")
      suspend fun fetchNews() : Response<ArticleResponse>

}