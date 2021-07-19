package com.stslex.wallpape.ui.main_screen_pager

import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.ImageModel
import com.stslex.splashgallery.databinding.ItemRecyclerPagerMainBinding
import com.stslex.splashgallery.utils.downloadAndSet
import com.stslex.splashgallery.utils.downloadAndSetSmallRound

class MainPagerViewHolder(private val binding: ItemRecyclerPagerMainBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(image: ImageModel) {
        binding.itemPagerImage.downloadAndSet(image.urls.small)
        binding.itemPagerAuthorName.text = image.user.name
        binding.itemPagerImagePerson.downloadAndSetSmallRound(image.user.profile_image.small)
    }
}