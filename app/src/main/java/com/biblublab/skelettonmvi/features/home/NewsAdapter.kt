package com.biblublab.skelettonmvi.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.biblublab.skelettonmvi.common.AdapterItem
import com.biblublab.skelettonmvi.databinding.ItemViewBinding

class NewsAdapter(private val listener : (View, news : NewsUi) -> Unit) : ListAdapter<AdapterItem, NewsAdapter.ItemViewHolder>(NewsItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder  {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(getItem(position))

    override fun getItemViewType(position: Int): Int = getItem(position).layoutId

    inner class ItemViewHolder(private val binding: ItemViewBinding ) : RecyclerView.ViewHolder(binding.root){

        fun bind(adapterItem: AdapterItem){
            when (adapterItem){
                is NewsListItem ->adapterItem.bind(binding, listener)
            }
        }
    }

}

internal class NewsItemDiffCallback : DiffUtil.ItemCallback<AdapterItem>(){

    override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean {//todo
        return false
    }

}