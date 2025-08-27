package com.stslex.splashgallery.feature_collections.ui

import androidx.recyclerview.widget.DiffUtil
import com.stslex.splashgallery.core_model.data.collection.CollectionModel

class CollectionsDiffItemCallback : DiffUtil.ItemCallback<CollectionModel>() {

    override fun areItemsTheSame(oldItem: CollectionModel, newItem: CollectionModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CollectionModel, newItem: CollectionModel): Boolean {
        return oldItem.title == newItem.title
    }
}