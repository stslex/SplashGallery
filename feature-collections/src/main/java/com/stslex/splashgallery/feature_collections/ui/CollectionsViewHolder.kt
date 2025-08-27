package com.stslex.splashgallery.feature_collections.ui

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.stslex.splashgallery.core_model.data.collection.CollectionModel
import com.stslex.splashgallery.core_ui.BaseViewHolder
import com.stslex.splashgallery.core_ui.OnClickListener
import com.stslex.splashgallery.core_ui.R
import com.stslex.splashgallery.core_ui.SetImageWithGlide
import com.stslex.splashgallery.core_ui.TextUtils.map
import com.stslex.splashgallery.feature_collections.databinding.ItemRecyclerCollectionsBinding

class CollectionsViewHolder(
    private val binding: ItemRecyclerCollectionsBinding,
    private val clickListener: OnClickListener,
    private val glide: SetImageWithGlide,
    private val isUser: Boolean
) : BaseViewHolder<CollectionModel>(binding) {

    override fun bind(item: CollectionModel) {
        item.setUserHead()
        item.setContent()
    }

    private fun CollectionModel.setContent() {
        with(binding) {
            numberTextView.apply {
                val photosLabel = context.resources.getString(R.string.label_photos)
                map("$total_photos $photosLabel")
            }
            titleTextView.map(title)
            collectionImageView.setImage(cover_photo.urls.regular, false)
            imageCardView.transitionName = id
            imageCardView.setOnClickListener(imageClickListener(title))
        }
    }

    private fun CollectionModel.setUserHead() {
        with(binding.userHead) {
            if (isUser) {
                userCardView.isVisible = false
            } else {
                userCardView.isVisible = true
                val userUrl = user.profile_image.medium
                userImageView.setImage(userUrl, true)
                usernameTextView.text = user.username
                userCardView.transitionName = user.username
                userCardView.setOnClickListener(userClickListener)
            }
        }
    }

    private fun ImageView.setImage(url: String, crop: Boolean) = glide.setImage(
        url = url,
        imageView = this,
        needCrop = true,
        needCircleCrop = crop
    )

    private val userClickListener = View.OnClickListener(clickListener::clickUser)

    private fun imageClickListener(title: String) = View.OnClickListener {
        clickListener.clickImage(it, title)
    }
}