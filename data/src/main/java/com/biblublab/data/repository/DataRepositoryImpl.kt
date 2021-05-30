package com.biblublab.data.repository

import com.biblublab.data.RestApi
import com.biblublab.data.feature.home.HomeDataMapper
import com.biblublab.domain.common.Either
import com.biblublab.domain.common.Failure
import com.biblublab.domain.feature.home.News
import com.biblublab.domain.repository.DataRepository
import retrofit2.Response
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val restApi: RestApi,
    private val homeDataMapper: HomeDataMapper,
    private val dao: NewsDao
) : DataRepository {

    override suspend fun fetchNews(): Either<Failure, List<News>> {
        val newsFromDb = getNewsFromDb()
        return when (val call = safeApiCall { restApi.fetchNews() }) {
            is Either.Failure -> if (newsFromDb.isNotEmpty()) Either.Successful(newsFromDb) else call
            is Either.Successful -> {
                homeDataMapper.toNewsList(call.success).forEach {
                    dao.insert(it)
                }
                Either.Successful(getNewsFromDb())
            }
        }
    }

    private suspend fun getNewsFromDb() = homeDataMapper.toNewsFromDBList(dao.getAllNews()).sortedByDescending { news -> news.publishedDate }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Either<Failure, T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    Either.Successful(it)
                } ?: Either.Failure(Failure.NO_DATA)
            } else {
                Either.Failure(Failure.NETWORK_ERROR)
            }
        } catch (e: Exception) {
            Either.Failure(Failure.UNKNOWN_ERROR)
        }
    }
}

