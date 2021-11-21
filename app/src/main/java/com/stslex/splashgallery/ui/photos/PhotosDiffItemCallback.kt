package com.stslex.splashgallery.ui.photos

import androidx.recyclerview.widget.DiffUtil
import com.stslex.splashgallery.data.model.ui.image.ImageModel


class PhotosDiffItemCallback : DiffUtil.ItemCallback<ImageModel>() {

    override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
        return oldItem.id == newItem.id
    }
}