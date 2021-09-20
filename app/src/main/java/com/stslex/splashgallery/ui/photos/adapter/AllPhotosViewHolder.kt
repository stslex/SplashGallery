package com.stslex.splashgallery.ui.photos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.ui.photos.PhotosUI
import com.stslex.splashgallery.utils.SetImageWithGlide


abstract class AllPhotosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: PhotosUI)

    class Base(
        private val binding: ItemRecyclerAllPhotosBinding,
        private val clickListener: ClickListener<PhotosUI>,
        private val setImageWithGlide: SetImageWithGlide,
        private val isUser: Boolean
    ) : AllPhotosViewHolder(binding.root) {
        override fun bind(item: PhotosUI) = with(binding) {
            if (isUser) {
                userCardView.visibility = View.VISIBLE
            } else {
                userCardView.visibility = View.GONE
            }
            item.bindPhotos(
                glide = setImageWithGlide,
                image = imageImageView,
                avatar = avatarImageView,
                username = usernameTextView,
                imageCardView = imageCardView,
                userCardView = userCardView
            )
            imageCardView.setOnClickListener {
                clickListener.clickImage(item)
            }
            userCardView.setOnClickListener {
                clickListener.clickUser(item)
            }
        }

    }
}
