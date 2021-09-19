package com.stslex.splashgallery.ui.photos.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.photos.PhotosUI
import com.stslex.splashgallery.utils.SetImageWithGlide
import com.stslex.splashgallery.utils.click_listeners.ImageClickListener

class AllPhotosViewHolder(private val binding: ItemRecyclerAllPhotosBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var clickListener: ImageClickListener
    private lateinit var id: String

    fun bind(imageModel: PhotosUI.Base, isUser: Boolean, setImage: SetImageWithGlide) {
        id = imageModel.imageId
        binding.itemPagerImage.transitionName = imageModel.imageUrl
        setImage.setImage(
            imageModel.imageUrl,
            binding.itemPagerImage,
            needCrop = false,
            needCircleCrop = false
        )
        binding.itemPagerAuthorName.transitionName = imageModel.userName
        if (isUser) {
            binding.itemPagerUserContainer.visibility = View.GONE
        } else {
            setImage.setImage(
                imageModel.userUrl,
                binding.itemPagerImagePerson,
                needCrop = false,
                needCircleCrop = true
            )
            binding.itemPagerAuthorName.text = imageModel.userName
        }
    }

    fun setClickListener(clickListener: ImageClickListener) {
        this.clickListener = clickListener
        binding.itemPagerImage.setOnClickListener(this)
        binding.itemPagerUserContainer.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0) {
            is ImageView -> clickListener.onImageClick(p0, id)
            is LinearLayout -> clickListener.userClickListener(binding.itemPagerAuthorName)
        }
    }

}