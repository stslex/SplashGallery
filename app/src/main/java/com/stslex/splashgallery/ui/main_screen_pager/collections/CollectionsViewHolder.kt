package com.stslex.splashgallery.ui.main_screen_pager.collections

import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.utils.downloadAndSet
import com.stslex.splashgallery.utils.downloadAndSetSmallRound

class CollectionsViewHolder(private val binding: ItemRecyclerCollectionsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(collection: CollectionModel) {
        binding.itemCollectionTitle.text = collection.title
        binding.itemCollectionImage.downloadAndSet(collection.cover_photo?.urls!!.regular)
        binding.itemCollectionAuthorName.text = collection.user?.name
        binding.itemCollectionDescription.text = collection.description
        binding.itemCollectionImagePerson.downloadAndSetSmallRound(collection.user?.profile_image!!.small)
    }
}