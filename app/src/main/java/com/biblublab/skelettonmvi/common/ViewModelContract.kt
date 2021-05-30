package com.biblublab.skelettonmvi.common

interface ViewModelContract<EVENT> {
    fun process(viewEvent: EVENT)
}