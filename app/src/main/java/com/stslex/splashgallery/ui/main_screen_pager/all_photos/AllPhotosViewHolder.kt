package com.stslex.splashgallery.ui.main_screen_pager.all_photos

import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.utils.downloadAndSet
import com.stslex.splashgallery.utils.downloadAndSetSmallRound

class AllPhotosViewHolder(private val binding: ItemRecyclerAllPhotosBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(image: ImageModel) {
        binding.itemPagerImage.downloadAndSet(image.urls.regular)
        binding.itemPagerImagePerson.downloadAndSetSmallRound(image.user?.profile_image!!.small)
        binding.itemPagerAuthorName.text = image.user.username
    }
}