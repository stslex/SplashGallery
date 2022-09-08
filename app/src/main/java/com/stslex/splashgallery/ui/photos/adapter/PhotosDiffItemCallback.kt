package com.stslex.splashgallery.ui.photos.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stslex.splashgallery.ui.model.ImageUIModel


class PhotosDiffItemCallback : DiffUtil.ItemCallback<ImageUIModel>() {

    override fun areItemsTheSame(
        oldItem: ImageUIModel,
        newItem: ImageUIModel
    ): Boolean = oldItem == newItem


    override fun areContentsTheSame(
        oldItem: ImageUIModel,
        newItem: ImageUIModel
    ): Boolean = oldItem.id == newItem.id
}