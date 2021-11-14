package com.stslex.splashgallery.ui.photos

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.model.image.ImageModel
import com.stslex.splashgallery.utils.SetImageWithGlide


class PhotosViewHolder(
    private val binding: ItemRecyclerAllPhotosBinding,
    private val glide: SetImageWithGlide,
    private val clickListener: OnClickListener,
    private val currentId: String
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageModel?) {
        with(binding) {
            if (item?.user?.username == currentId) {
                userCardView.visibility = View.GONE
            } else {
                userCardView.visibility = View.VISIBLE
                glide.setImage(
                    url = item?.user?.profile_image?.medium!!,
                    imageView = avatarImageView,
                    needCrop = true,
                    needCircleCrop = true
                )
                usernameTextView.text = item.user.username
                userCardView.transitionName = item.user.username
                userCardView.setOnClickListener {
                    clickListener.clickUser(it)
                }
            }
            glide.setImage(
                url = item.urls.regular,
                imageView = imageImageView,
                needCrop = true,
                needCircleCrop = false
            )
            imageCardView.transitionName = item.id
            imageCardView.setOnClickListener {
                clickListener.clickImage(it, item.urls.regular)
            }
        }
    }
}