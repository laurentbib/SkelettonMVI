package com.biblublab.domain.feature.home

import com.biblublab.domain.common.Either
import com.biblublab.domain.common.Failure
import com.biblublab.domain.common.FlowUseCase
import com.biblublab.domain.common.UseCase
import com.biblublab.domain.feature.home.News
import com.biblublab.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val dataRepository: DataRepository) : UseCase<Failure, List<News>>()  {

    override suspend  fun run(): Either<Failure, List<News>> {
        return dataRepository.fetchNews()
    }
}