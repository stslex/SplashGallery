package com.stslex.splashgallery.ui.photos.adapter

import com.stslex.core_ui.BasePagingAdapter
import com.stslex.core_ui.OnClickListener
import com.stslex.core_ui.SetImageWithGlide
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.model.ImageUIModel

class PhotosAdapter(
    private val clickListener: OnClickListener,
    private val glide: SetImageWithGlide,
    private val isUser: Boolean
) : BasePagingAdapter<ItemRecyclerAllPhotosBinding, ImageUIModel, PhotosViewHolder>(
    bindingInflater = ItemRecyclerAllPhotosBinding::inflate,
    diffUtil = PhotosDiffItemCallback()
) {

    override val viewHolder: (ItemRecyclerAllPhotosBinding) -> PhotosViewHolder
        get() = { binding ->
            PhotosViewHolder(
                binding = binding,
                glide = glide,
                clickListener = clickListener,
                isUser = isUser
            )
        }
}