package com.stslex.splashgallery.ui.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.model.ImageUIModel
import com.stslex.core_ui.SetImageWithGlide

class PhotosAdapter(
    private val clickListener: OnClickListener,
    private val glide: SetImageWithGlide,
    private val isUser: Boolean
) : PagingDataAdapter<ImageUIModel, PhotosViewHolder>(PhotosDiffItemCallback()) {

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerAllPhotosBinding.inflate(inflater, parent, false)
        return PhotosViewHolder(
            binding = binding,
            glide = glide,
            clickListener = clickListener,
            isUser = isUser
        )
    }
}