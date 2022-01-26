package com.stslex.splashgallery.ui.photos.loader_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import com.stslex.splashgallery.databinding.ItemErrorBinding

class PhotosErrorViewHolder internal constructor(
    private val binding: ItemErrorBinding
) : PhotosItemViewHolder(binding.root) {

    override fun bind(loadState: LoadState) {
        require(loadState is LoadState.Error)
        binding.errorMessage.text = loadState.error.localizedMessage
    }

    companion object {
        operator fun invoke(
            layoutInflater: LayoutInflater,
            parent: ViewGroup? = null,
            attachToRoot: Boolean = false
        ): PhotosErrorViewHolder {
            return PhotosErrorViewHolder(
                ItemErrorBinding.inflate(layoutInflater, parent, attachToRoot)
            )
        }
    }
}