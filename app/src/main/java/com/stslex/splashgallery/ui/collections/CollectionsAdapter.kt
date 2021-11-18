package com.stslex.splashgallery.ui.collections

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.model.collection.CollectionModel
import com.stslex.splashgallery.utils.glide.SetImageWithGlide

class CollectionsAdapter(
    private val clickListener: OnClickListener,
    private val glide: SetImageWithGlide,
    private val isUser: Boolean,
    context: Context
) : PagingDataAdapter<CollectionModel, CollectionsViewHolder>(CollectionsDiffItemCallback()) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder =
        CollectionsViewHolder(
            binding = ItemRecyclerCollectionsBinding.inflate(layoutInflater, parent, false),
            clickListener = clickListener,
            glide = glide,
            isUser = isUser
        )
}