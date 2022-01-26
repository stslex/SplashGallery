package com.stslex.splashgallery.ui.photos.loader_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import com.stslex.splashgallery.databinding.ItemProgressBinding

class PhotosProgressViewHolder internal constructor(
    binding: ItemProgressBinding
) : PhotosItemViewHolder(binding.root) {

    override fun bind(loadState: LoadState) {
        // Do nothing
    }

    companion object {

        operator fun invoke(
            layoutInflater: LayoutInflater,
            parent: ViewGroup? = null,
            attachToRoot: Boolean = false
        ): PhotosProgressViewHolder {
            return PhotosProgressViewHolder(
                ItemProgressBinding.inflate(layoutInflater, parent, attachToRoot)
            )
        }
    }
}