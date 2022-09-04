package com.stslex.splashgallery.ui.photos.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.stslex.core_ui.SetImageWithGlide
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.model.ImageUIModel
import com.stslex.splashgallery.ui.utils.TextUtils.map


class PhotosViewHolder(
    private val binding: ItemRecyclerAllPhotosBinding,
    private val glide: SetImageWithGlide,
    private val clickListener: OnClickListener,
    private val isUser: Boolean
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageUIModel) {

        with(binding) {
            if (isUser) {
                userHead.userCardView.isVisible = false
            } else {
                userHead.userCardView.isVisible = true
                glide.setImage(
                    url = item.url,
                    imageView = userHead.userImageView,
                    needCrop = true,
                    needCircleCrop = true
                )
                userHead.usernameTextView.map(item.user.username)
                userHead.userCardView.transitionName = item.user.username
                userHead.userCardView.setOnClickListener(clickListener::clickUser)
            }

            glide.setImage(
                item.url,
                imageImageView,
                needCrop = true,
                needCircleCrop = false
            )
            imageCardView.transitionName = item.id
            imageCardView.setOnClickListener(clickListener.imageClick(item.url))
        }

    }

    private fun OnClickListener.imageClick(url: String) = View.OnClickListener { view ->
        clickImage(view, url)
    }
}