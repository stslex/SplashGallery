package com.stslex.splashgallery.ui.photos.loader_adapter

import android.view.View
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

abstract class PhotosItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(loadState: LoadState)
}