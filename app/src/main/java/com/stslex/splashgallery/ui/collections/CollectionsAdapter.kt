package com.stslex.splashgallery.ui.collections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.stslex.splashgallery.data.model.data.collection.CollectionModel
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.core_ui.SetImageWithGlide

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