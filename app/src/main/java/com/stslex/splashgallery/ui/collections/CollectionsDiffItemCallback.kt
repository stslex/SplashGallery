package com.stslex.splashgallery.ui.collections

import androidx.recyclerview.widget.DiffUtil
import com.stslex.splashgallery.ui.model.collection.CollectionModel

class CollectionsDiffItemCallback : DiffUtil.ItemCallback<CollectionModel>() {

    override fun areItemsTheSame(oldItem: CollectionModel, newItem: CollectionModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CollectionModel, newItem: CollectionModel): Boolean {
        return oldItem.title == newItem.title
    }
}