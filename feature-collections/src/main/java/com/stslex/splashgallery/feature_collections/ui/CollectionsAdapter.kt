package com.stslex.splashgallery.feature_collections.ui

import com.stslex.splashgallery.core_model.data.collection.CollectionModel
import com.stslex.splashgallery.core_ui.BasePagingAdapter
import com.stslex.splashgallery.core_ui.OnClickListener
import com.stslex.splashgallery.core_ui.SetImageWithGlide
import com.stslex.splashgallery.feature_collections.databinding.ItemRecyclerCollectionsBinding

class CollectionsAdapter(
    private val clickListener: OnClickListener,
    private val glide: SetImageWithGlide,
    private val isUser: Boolean
) : BasePagingAdapter<ItemRecyclerCollectionsBinding, CollectionModel, CollectionsViewHolder>(
    bindingInflater = ItemRecyclerCollectionsBinding::inflate,
    diffUtil = CollectionsDiffItemCallback()
) {

    override val viewHolder: (ItemRecyclerCollectionsBinding) -> CollectionsViewHolder
        get() = { binding ->
            CollectionsViewHolder(
                binding = binding,
                clickListener = clickListener,
                glide = glide,
                isUser = isUser
            )
        }
}