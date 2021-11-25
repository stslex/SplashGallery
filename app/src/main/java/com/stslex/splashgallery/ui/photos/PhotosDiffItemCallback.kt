package com.stslex.splashgallery.ui.photos

import androidx.recyclerview.widget.DiffUtil


class PhotosDiffItemCallback : DiffUtil.ItemCallback<ImageUI>() {

    override fun areItemsTheSame(oldItem: ImageUI, newItem: ImageUI): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ImageUI, newItem: ImageUI): Boolean {
        return oldItem.same(newItem)
    }
}