package com.biblublab.skelettonmvi.features.detail

import com.biblublab.skelettonmvi.features.home.NewsUi

data class DetailState (val newsDetailed : NewsUi)

sealed class DetailEffect{
    object BackToHome : DetailEffect()
}

sealed class DetailEvent{
    data class ShowDetail(val newsDetailed : NewsUi) : DetailEvent()
}
