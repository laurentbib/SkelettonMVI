package com.biblublab.domain.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn


@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
abstract class FlowUseCase<out FailureType, out SuccessType>  {

    abstract  fun run(): Flow<Either<FailureType, SuccessType>>

     operator fun invoke(): Flow<Either<FailureType, SuccessType>> {
        return run()
            .distinctUntilChanged()
            .catch{ Either.Failure(Failure.UNKNOWN_ERROR) }
    .flowOn(Dispatchers.IO)
}


}