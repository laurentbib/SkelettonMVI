package com.biblublab.skelettonmvi.features.home

import android.view.View
import com.biblublab.domain.feature.home.News
import com.biblublab.skelettonmvi.R
import com.biblublab.skelettonmvi.common.AdapterItem
import com.biblublab.skelettonmvi.databinding.ItemViewBinding
import com.biblublab.skelettonmvi.utils.loadImg
import kotlinx.android.synthetic.main.item_view.view.*

class NewsListItem(private val news : NewsUi) : AdapterItem{

    override val layoutId: Int = R.layout.item_view

    fun bind(binding : ItemViewBinding, listener : (View, news : NewsUi) -> Unit) {
        binding.newsTitle.text = news.title
        binding.newsDescription.text = news.description
        binding.newsImage.loadImg(news.urlImage, R.drawable.ic_baseline_block_24)
        binding.root.setOnClickListener{listener(it, news)}
    }

}