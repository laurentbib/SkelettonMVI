package com.biblublab.skelettonmvi.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.biblublab.domain.feature.home.GetNewsUseCase
import com.biblublab.skelettonmvi.common.BaseViewModel
import com.biblublab.skelettonmvi.common.FetchStatus
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.launch

@ActivityScoped
class HomeViewModel @ViewModelInject constructor(private val getNewsUseCase: GetNewsUseCase,
                                                 private val homeUiMapper: HomeUiMapper) : BaseViewModel<HomeState, HomeEffect, HomeEvent>(){

    init {
        viewState = HomeState(FetchStatus.NotFetched, newsList = emptyList())
    }

    override fun process(viewEvent: HomeEvent) {
        super.process(viewEvent)
        when(viewEvent){
            HomeEvent.FetchNews -> fetchNews()
            is HomeEvent.OnClickItem -> viewEffect = HomeEffect.OpenDetail(viewEvent.itemView, viewEvent.news)
        }
    }

    private fun fetchNews(){
        viewState = viewState.copy(fetchStatus = FetchStatus.Fetching)
        viewModelScope.launch {
            getNewsUseCase.invoke().either(
               {
                    viewState = viewState.copy(fetchStatus = FetchStatus.Fetched, newsList = emptyList())
                },{newsList ->
                    viewState = viewState.copy(fetchStatus = FetchStatus.Fetched, newsList = newsList.map { news ->NewsListItem(homeUiMapper.toNewsUi(news)) })
            })
        }
    }
}