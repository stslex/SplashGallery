package com.stslex.splashgallery.ui.collections

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.ui.collection.CollectionModel
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.utils.AppResources.photos
import com.stslex.splashgallery.ui.utils.SetImageWithGlide

class CollectionsViewHolder(
    private val binding: ItemRecyclerCollectionsBinding,
    private val clickListener: OnClickListener,
    private val glide: SetImageWithGlide,
    private val isUser: Boolean
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: CollectionModel) {
        with(binding) {
            if (isUser) {
                userCardView.visibility = View.GONE
            } else {
                userCardView.visibility = View.VISIBLE
                val userUrl = item.user.profile_image.medium
                userImageView.setImage(userUrl, true)
                usernameTextView.text = item.user.username
                userCardView.transitionName = item.user.username
                userCardView.setOnClickListener(userClickListener)
            }
            numberTextView.text = "${item.total_photos} $photos"
            titleTextView.text = item.title
            val collectionUrl = item.cover_photo.urls.regular
            collectionImageView.setImage(collectionUrl, false)
            imageCardView.transitionName = item.id
            imageCardView.setOnClickListener(item.title.imageClickListener)
        }
    }

    private fun ImageView.setImage(url: String, crop: Boolean) = glide.setImage(
        url = url,
        imageView = this,
        needCrop = true,
        needCircleCrop = crop
    )

    private val userClickListener = View.OnClickListener {
        clickListener.clickUser(it)
    }

    private val String?.imageClickListener: View.OnClickListener
        get() = View.OnClickListener {
            clickListener.clickImage(it, toString())
        }
}