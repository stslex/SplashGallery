package com.stslex.splashgallery.ui.photos.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stslex.splashgallery.ui.photos.models.ImageUI


class PhotosDiffItemCallback : DiffUtil.ItemCallback<ImageUI>() {

    override fun areItemsTheSame(oldItem: ImageUI, newItem: ImageUI): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ImageUI, newItem: ImageUI): Boolean {
        return oldItem.same(newItem)
    }
}