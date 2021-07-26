package com.stslex.splashgallery.ui.main_screen_pager.collections

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.databinding.ItemRecyclerPagerMainBinding
import com.stslex.splashgallery.utils.downloadAndSet
import com.stslex.splashgallery.utils.downloadAndSetSmallRound

class CollectionsViewHolder(private val binding: ItemRecyclerPagerMainBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(collection: CollectionModel) {
        binding.itemPagerCollectionContainer.visibility = View.VISIBLE
        binding.itemPagerCollectionTitle.text = collection.title
        binding.itemPagerImage.downloadAndSet(collection.cover_photo?.urls!!.regular)
        binding.itemPagerAuthorName.text = collection.user?.name
        binding.itemPagerImagePerson.downloadAndSetSmallRound(collection.user?.profile_image!!.small)
    }
}