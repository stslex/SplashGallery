package com.stslex.splashgallery.ui.all_photos.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.utils.click_listeners.ImageClickListener
import com.stslex.splashgallery.utils.downloadAndSet
import com.stslex.splashgallery.utils.downloadAndSetSmallRound

class AllPhotosViewHolder(private val binding: ItemRecyclerAllPhotosBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var clickListener: ImageClickListener
    private lateinit var id: String

    fun bind(imageModel: ImageModel) {
        id = imageModel.id
        binding.itemPagerUserContainer.transitionName = imageModel.user?.id
        binding.itemPagerImage.transitionName = imageModel.urls.regular
        binding.itemPagerImage.downloadAndSet(imageModel.urls.regular)
        binding.itemPagerImagePerson.downloadAndSetSmallRound(imageModel.user?.profile_image!!.medium)
        binding.itemPagerAuthorName.text = imageModel.user.username
    }

    fun setClickListener(clickListener: ImageClickListener) {
        this.clickListener = clickListener
        binding.itemPagerImage.setOnClickListener(this)
        binding.itemPagerUserContainer.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0) {
            is ImageView -> clickListener.onImageClick(p0, id)
            is LinearLayout -> clickListener.userClickListener(p0)
        }
    }

}