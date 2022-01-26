package com.stslex.splashgallery.ui.photos.adapter

import android.view.View
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

    fun bind(item: ImageUI) = with(binding) {
        with(userHead) {
            if (isUser) item.hideUserHead(userCardView)
            else {
                item.bindUser(glide, userImageView, usernameTextView, userCardView)
                userCardView.setOnClickListener(userClick)
            }
        }
        item.bindImage(glide, imageImageView, imageCardView)
        imageCardView.setOnClickListener(item.getUrl().imageClick)
    }

    private val userClick = View.OnClickListener {
        clickListener.clickUser(binding.userHead.userCardView)
    }

    private val String.imageClick
        get() = View.OnClickListener {
            clickListener.clickImage(it, this)
        }
}