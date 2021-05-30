package com.biblublab.skelettonmvi.features.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.biblublab.skelettonmvi.R
import com.biblublab.skelettonmvi.common.BaseActivity
import com.biblublab.skelettonmvi.databinding.ActivityDetailBinding
import com.biblublab.skelettonmvi.features.home.NewsUi
import com.biblublab.skelettonmvi.utils.loadImg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<DetailState, DetailEffect, DetailEvent, DetailViewModel, ActivityDetailBinding>({ActivityDetailBinding.inflate(it)}) {

 override val viewModel : DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newsUi = intent.getParcelableExtra<NewsUi>("newsParcelable")
        viewModel.process(DetailEvent.ShowDetail(newsUi))
    }

    override fun renderViewState(viewState: DetailState) {
        binding.detailImage.loadImg(viewState.newsDetailed.urlImage, R.drawable.ic_baseline_block_24)
        binding.detailTitle.text = viewState.newsDetailed.title
    }

    override fun renderViewEffect(viewEffect: DetailEffect) {
        when(viewEffect){
            DetailEffect.BackToHome -> TODO()
        }
    }
}