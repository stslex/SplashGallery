package com.stslex.feature_collections.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.stslex.core_model.data.collection.CollectionModel
import com.stslex.core_ui.OnClickListener
import com.stslex.core_ui.SetImageWithGlide
import com.stslex.feature_collections.databinding.ItemRecyclerCollectionsBinding

class CollectionsAdapter(
    private val clickListener: OnClickListener,
    private val glide: SetImageWithGlide,
    private val isUser: Boolean
) : PagingDataAdapter<CollectionModel, CollectionsViewHolder>(CollectionsDiffItemCallback()) {

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerCollectionsBinding.inflate(inflater, parent, false)
        return CollectionsViewHolder(
            binding = binding,
            clickListener = clickListener,
            glide = glide,
            isUser = isUser
        )
    }
}