package com.stslex.splashgallery.ui.photos.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.photos.models.ImageUI
import com.stslex.splashgallery.ui.utils.SetImageWithGlide


class PhotosViewHolder(
    private val binding: ItemRecyclerAllPhotosBinding,
    private val glide: SetImageWithGlide,
    private val clickListener: OnClickListener,
    private val isUser: Boolean
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageUI) {
        with(binding) {
            with(binding.userHead) {
                item.bind(
                    isUser = isUser,
                    clickListener = clickListener,
                    glide = glide,
                    imageView = imageImageView,
                    imageCardView = imageCardView,
                    userImageView = userImageView,
                    userTextView = usernameTextView,
                    userCardView = userCardView,
                )
            }
        }
    }
}