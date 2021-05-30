package com.biblublab.domain.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<out FailureType, out SuccessType> {

    abstract suspend fun run(): Either<FailureType, SuccessType>

    suspend operator fun invoke(): Either<FailureType, SuccessType> {
        return withContext(Dispatchers.IO) { run() }
    }
}