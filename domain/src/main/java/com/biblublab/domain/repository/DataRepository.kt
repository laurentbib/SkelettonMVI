package com.biblublab.domain.repository

import com.biblublab.domain.common.Either
import com.biblublab.domain.common.Failure
import com.biblublab.domain.feature.home.News
import kotlinx.coroutines.flow.Flow

interface DataRepository {
     suspend fun fetchNews() : Either<Failure, List<News>>
}