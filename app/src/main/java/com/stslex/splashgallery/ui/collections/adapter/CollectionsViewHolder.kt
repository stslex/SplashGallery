package com.stslex.splashgallery.ui.collections.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.utils.Resources.photos
import com.stslex.splashgallery.utils.SetImageWithGlide
import com.stslex.splashgallery.utils.click_listeners.CollectionClickListener

class CollectionsViewHolder(private val binding: ItemRecyclerCollectionsBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var clickListener: CollectionClickListener
    private lateinit var title: String

    @SuppressLint("SetTextI18n")
    fun bind(collection: CollectionModel, isUser: Boolean, setImage: SetImageWithGlide) {
        title = collection.title
        binding.itemCollectionAuthorName.transitionName = collection.user?.username
        binding.itemCollectionImage.transitionName = collection.id
        binding.itemCollectionTitle.text = title
        binding.itemCollectionNumber.text = "${collection.total_photos} $photos"
        setImage.makeGlideImage(
            collection.cover_photo?.urls!!.regular,
            binding.itemCollectionImage,
            false,
            false
        )

        if (isUser) {
            binding.itemCollectionUserContainer.visibility = View.GONE
        } else {
            binding.itemCollectionAuthorName.text = collection.user?.name
            setImage.makeGlideImage(
                collection.user?.profile_image!!.medium,
                binding.itemCollectionImagePerson,
                false,
                true
            )
        }
    }

    fun setClickListener(clickListener: CollectionClickListener) {
        this.clickListener = clickListener
        binding.itemCollectionImage.setOnClickListener(this)
        binding.itemCollectionUserContainer.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            is ImageView -> clickListener.onImageClick(p0, title)
            is LinearLayout -> clickListener.onUserClick(binding.itemCollectionAuthorName)
        }

    }
}