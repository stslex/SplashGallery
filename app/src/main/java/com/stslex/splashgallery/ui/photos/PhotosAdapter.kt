package com.stslex.splashgallery.ui.photos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.model.image.ImageModel
import com.stslex.splashgallery.utils.SetImageWithGlide

class PhotosAdapter(
    private val clickListener: OnClickListener,
    private val glide: SetImageWithGlide,
    context: Context
) : PagingDataAdapter<ImageModel, PhotosViewHolder>(PhotosDiffItemCallback()) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            binding = ItemRecyclerAllPhotosBinding.inflate(layoutInflater, parent, false),
            glide = glide,
            clickListener = clickListener
        )
    }
}