package com.stslex.splashgallery.ui.photos.adapter

import android.view.View
import androidx.core.view.isVisible
import com.stslex.splashgallery.core_ui.BaseViewHolder
import com.stslex.splashgallery.core_ui.OnClickListener
import com.stslex.splashgallery.core_ui.SetImageWithGlide
import com.stslex.splashgallery.core_ui.TextUtils.map
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.model.ImageUIModel


class PhotosViewHolder(
    private val binding: ItemRecyclerAllPhotosBinding,
    private val glide: SetImageWithGlide,
    private val clickListener: OnClickListener,
    private val isUser: Boolean
) : BaseViewHolder<ImageUIModel>(binding) {

    override fun bind(item: ImageUIModel) {

        with(binding) {
            if (isUser) {
                userHead.userCardView.isVisible = false
            } else {
                userHead.userCardView.isVisible = true
                glide.setImage(
                    url = item.user.url,
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