package com.stslex.splashgallery.ui.collections.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.utils.click_listeners.CollectionClickListener
import com.stslex.splashgallery.utils.downloadAndSet
import com.stslex.splashgallery.utils.downloadAndSetSmallRound

class CollectionsViewHolder(private val binding: ItemRecyclerCollectionsBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var clickListener: CollectionClickListener
    private lateinit var title: String

    fun bind(collection: CollectionModel) {
        title = collection.title
        binding.itemCollectionImage.transitionName = collection.id
        binding.itemCollectionTitle.text = title
        binding.itemCollectionImage.downloadAndSet(collection.cover_photo?.urls!!.regular)
        binding.itemCollectionAuthorName.text = collection.user?.name
        binding.itemCollectionNumber.text = "${collection.total_photos} photos"
        binding.itemCollectionImagePerson.downloadAndSetSmallRound(collection.user?.profile_image!!.small)
    }

    fun setClickListener(clickListener: CollectionClickListener) {
        this.clickListener = clickListener
        binding.itemCollectionImage.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            is ImageView -> clickListener.onClick(p0, title)
        }
    }
}