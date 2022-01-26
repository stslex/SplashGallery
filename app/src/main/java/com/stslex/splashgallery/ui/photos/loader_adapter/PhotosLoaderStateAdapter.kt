package com.stslex.splashgallery.ui.photos.loader_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PhotosLoaderStateAdapter : LoadStateAdapter<PhotosItemViewHolder>() {

    override fun getStateViewType(loadState: LoadState) = when (loadState) {
        is LoadState.NotLoading -> error("Not supported")
        is LoadState.Loading -> PROGRESS
        is LoadState.Error -> ERROR
    }

    override fun onBindViewHolder(holder: PhotosItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PhotosItemViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return when (loadState) {
            is LoadState.Loading -> PhotosProgressViewHolder(inflater, parent)
            is LoadState.Error -> PhotosErrorViewHolder(inflater, parent)
            is LoadState.NotLoading -> error("Not supported")
        }
    }

    private companion object {
        private const val ERROR = 1
        private const val PROGRESS = 0
    }
}