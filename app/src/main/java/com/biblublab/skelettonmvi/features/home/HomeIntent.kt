package com.biblublab.skelettonmvi.features.home

import android.view.View
import com.biblublab.domain.feature.home.News
import com.biblublab.skelettonmvi.common.AdapterItem
import com.biblublab.skelettonmvi.common.FetchStatus

data class HomeState (val fetchStatus: FetchStatus, val newsList : List<AdapterItem>)

sealed class HomeEffect{
    data class OpenDetail(val itemView : View, val news: NewsUi) : HomeEffect()
}

sealed class HomeEvent{
    object FetchNews : HomeEvent()
    data class OnClickItem(val itemView : View, val news: NewsUi) : HomeEvent()

}