package com.stslex.splashgallery.ui.photos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.ui.photos.PhotosUI


abstract class AllPhotosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: PhotosUI)

    class Base(
        private val binding: ItemRecyclerAllPhotosBinding,
        private val clickListener: ClickListener<PhotosUI>
    ) :
        AllPhotosViewHolder(binding.root) {
        override fun bind(item: PhotosUI) = with(binding) {
            item.bindPhotos(
                image = imageImageView,
                avatar = avatarImageView,
                username = usernameTextView,
                imageCardView = imageCardView,
                userCardView = userCardView
            )
            imageCardView.setOnClickListener {
                clickListener.click(item)
            }
            userCardView.setOnClickListener {
                clickListener.click(item)
            }
        }

    }
}
