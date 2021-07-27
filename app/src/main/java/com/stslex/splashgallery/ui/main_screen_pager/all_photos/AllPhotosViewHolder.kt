package com.stslex.splashgallery.ui.main_screen_pager.all_photos

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.utils.downloadAndSet
import com.stslex.splashgallery.utils.downloadAndSetSmallRound

class AllPhotosViewHolder(private val binding: ItemRecyclerAllPhotosBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var clickListener: AllPhotosClickListener
    private lateinit var imageModel: ImageModel

    fun bind(imageModel: ImageModel, position: Int) {
        this.imageModel = imageModel
        binding.itemPagerImage.transitionName = "image$position"
        binding.itemPagerImage.downloadAndSet(imageModel.urls.regular)
        binding.itemPagerImagePerson.downloadAndSetSmallRound(imageModel.user?.profile_image!!.small)
        binding.itemPagerAuthorName.text = imageModel.user.username
    }

    fun setClickListener(clickListener: AllPhotosClickListener) {
        this.clickListener = clickListener
        binding.itemPagerImage.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0) {
            is ImageView -> clickListener.onClick(imageModel, p0)
        }
    }
}