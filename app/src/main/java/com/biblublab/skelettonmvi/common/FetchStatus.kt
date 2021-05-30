package com.biblublab.skelettonmvi.common

sealed class FetchStatus {
    object Fetching : FetchStatus()
    object Fetched : FetchStatus()
    object NotFetched : FetchStatus()
}