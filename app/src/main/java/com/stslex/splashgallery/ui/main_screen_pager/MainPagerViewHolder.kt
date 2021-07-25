package com.stslex.wallpape.ui.main_screen_pager

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.databinding.ItemRecyclerPagerMainBinding
import com.stslex.splashgallery.utils.downloadAndSet
import com.stslex.splashgallery.utils.downloadAndSetSmallRound

class MainPagerViewHolder(private val binding: ItemRecyclerPagerMainBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(image: ImageModel) {
        binding.itemPagerCollectionContainer.visibility = View.GONE
        binding.itemPagerImage.downloadAndSet(image.urls.regular)
        binding.itemPagerAuthorName.text = image.user?.name
        binding.itemPagerImagePerson.downloadAndSetSmallRound(image.user?.profile_image!!.small)
    }

    fun bind(collection: CollectionModel) {
        binding.itemPagerCollectionContainer.visibility = View.VISIBLE
        binding.itemPagerCollectionTitle.text = collection.title
        binding.itemPagerImage.downloadAndSet(collection.cover_photo?.urls!!.regular)
        binding.itemPagerAuthorName.text = collection.user?.name
        binding.itemPagerImagePerson.downloadAndSetSmallRound(collection.user?.profile_image!!.small)
    }
}