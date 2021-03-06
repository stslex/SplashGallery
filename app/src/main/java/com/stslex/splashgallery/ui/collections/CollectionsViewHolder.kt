package com.stslex.splashgallery.ui.collections

import android.view.View
import android.widget.ImageView
import com.stslex.splashgallery.R
import com.stslex.splashgallery.data.model.ui.collection.CollectionModel
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.ui.core.BaseViewHolder
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.utils.SetImageWithGlide

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
                userCardView.hide()
            } else {
                userCardView.show()
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