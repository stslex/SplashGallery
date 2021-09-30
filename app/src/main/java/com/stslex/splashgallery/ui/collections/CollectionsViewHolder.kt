package com.stslex.splashgallery.ui.collections

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.model.collection.CollectionModel
import com.stslex.splashgallery.utils.Resources.currentId
import com.stslex.splashgallery.utils.Resources.photos
import com.stslex.splashgallery.utils.SetImageWithGlide

class CollectionsViewHolder(
    private val binding: ItemRecyclerCollectionsBinding,
    private val clickListener: OnClickListener,
    private val glide: SetImageWithGlide
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: CollectionModel?) {
        with(binding) {
            if (item?.user?.username == currentId) {
                userCardView.visibility = View.GONE
            } else {
                userCardView.visibility = View.VISIBLE
                glide.setImage(
                    url = item?.user?.profile_image?.medium!!,
                    imageView = userImageView,
                    needCrop = true,
                    needCircleCrop = true
                )
                usernameTextView.text = item.user.username
                userCardView.transitionName = item.user.username
                userCardView.setOnClickListener {
                    clickListener.clickUser(it)
                }
            }
            numberTextView.text = item.total_photos.toString() + photos
            titleTextView.text = item.title
            glide.setImage(
                url = item.cover_photo?.urls?.regular.toString(),
                imageView = collectionImageView,
                needCrop = true,
                needCircleCrop = false
            )
            imageCardView.transitionName = item.id
            imageCardView.setOnClickListener {
                clickListener.clickImage(it, item.title)
            }
        }
    }
}