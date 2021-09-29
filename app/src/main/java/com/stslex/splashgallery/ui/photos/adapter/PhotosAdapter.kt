package com.stslex.splashgallery.ui.photos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.utils.SetImageWithGlide

class PhotosAdapter(private val glide: SetImageWithGlide, context: Context) :
    PagingDataAdapter<ImageModel, PhotosViewHolder>(PhotosDiffItemCallback) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            ItemRecyclerAllPhotosBinding.inflate(layoutInflater, parent, false),
            glide
        )
    }
}

class PhotosViewHolder(
    private val binding: ItemRecyclerAllPhotosBinding,
    private val glide: SetImageWithGlide
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageModel?) {
        with(binding) {
            glide.setImage(
                url = item?.urls!!.regular,
                imageView = imageImageView,
                needCrop = true,
                needCircleCrop = false
            )
            glide.setImage(
                url = item.user?.profile_image?.medium!!,
                imageView = avatarImageView,
                needCrop = true,
                needCircleCrop = true
            )
            usernameTextView.text = item.user.username
        }
    }
}

private object PhotosDiffItemCallback : DiffUtil.ItemCallback<ImageModel>() {

    override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
        return oldItem.id == newItem.id
    }
}