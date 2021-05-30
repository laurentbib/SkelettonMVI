package com.biblublab.skelettonmvi.features.detail

import androidx.hilt.lifecycle.ViewModelInject
import com.biblublab.skelettonmvi.common.BaseViewModel
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
class DetailViewModel @ViewModelInject constructor() : BaseViewModel<DetailState, DetailEffect, DetailEvent>() {


    override fun process(viewEvent: DetailEvent) {
        super.process(viewEvent)
        when(viewEvent){
            is DetailEvent.ShowDetail ->  viewState = DetailState(viewEvent.newsDetailed)
        }
    }
}