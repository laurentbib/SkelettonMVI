package com.biblublab.skelettonmvi.features.home

import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.biblublab.skelettonmvi.R
import com.biblublab.skelettonmvi.common.BaseActivity
import com.biblublab.skelettonmvi.common.FetchStatus
import com.biblublab.skelettonmvi.databinding.ActivityMainBinding
import com.biblublab.skelettonmvi.features.detail.DetailActivity
import com.biblublab.skelettonmvi.utils.beginShimmerAnim
import com.biblublab.skelettonmvi.utils.endShimmerAnim
import com.biblublab.skelettonmvi.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.view.*

@AndroidEntryPoint
class MainActivity : BaseActivity<HomeState, HomeEffect, HomeEvent, HomeViewModel, ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    override val viewModel: HomeViewModel by viewModels()

    private val newsAdapter by lazy {
        NewsAdapter {view, news ->
            viewModel.process(HomeEvent.OnClickItem(view, news))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.newsListView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = newsAdapter
        }
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.process(HomeEvent.FetchNews)
        }
    }


    override fun renderViewState(viewState: HomeState) {
        when (viewState.fetchStatus) {
            FetchStatus.Fetching -> {
                binding.newsListView.visible(false)
                binding.loadingShimmer.beginShimmerAnim()
            }
            FetchStatus.Fetched -> {
                binding.swipeToRefresh.isRefreshing = false
                binding.loadingShimmer.endShimmerAnim()
                binding.newsListView.visible(true)
            }
            FetchStatus.NotFetched -> {
                viewModel.process(HomeEvent.FetchNews)
            }
        }
        newsAdapter.submitList(viewState.newsList)
    }

    override fun renderViewEffect(viewEffect: HomeEffect) {
        when(viewEffect){
            is HomeEffect.OpenDetail -> {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("newsParcelable", viewEffect.news)
                val options = ActivityOptions.makeSceneTransitionAnimation(this,Pair(viewEffect.itemView.newsImage, "image"), Pair(viewEffect.itemView.newsTitle, "text"))
                startActivity(intent, options.toBundle())
            }
        }
    }
}