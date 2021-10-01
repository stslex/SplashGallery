package com.stslex.splashgallery.ui.photos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import coil.request.ImageRequest
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.ui.model.image.ImageModel

class PhotosAdapter(
    private val clickListener: ClickListener,
    context: Context,
    private val coilListener: ImageRequest.Listener
) : PagingDataAdapter<ImageModel, PhotosViewHolder>(PhotosDiffItemCallback()) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            binding = ItemRecyclerAllPhotosBinding.inflate(layoutInflater, parent, false),
            clickListener = clickListener,
            coilListener = coilListener
        )
    }
}