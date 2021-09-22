package com.stslex.splashgallery.ui.collections.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.ui.collections.CollectionUI
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.utils.SetImageWithGlide

abstract class CollectionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: CollectionUI)
    class Base(
        private val binding: ItemRecyclerCollectionsBinding,
        private val clickListener: ClickListener<CollectionUI>,
        private val setImageWithGlide: SetImageWithGlide,
        private val isUser: Boolean
    ) : CollectionsViewHolder(binding.root) {
        override fun bind(item: CollectionUI) {
            if (isUser) {
                binding.userCardView.visibility = View.GONE
            } else binding.userCardView.visibility = View.VISIBLE
            item.bindCollections(
                glide = setImageWithGlide,
                collectionTitle = binding.titleTextView,
                collectionImage = binding.collectionImageView,
                collectionNum = binding.numberTextView,
                usernameTextView = binding.usernameTextView,
                userImage = binding.userImageView,
                userCardView = binding.userCardView,
                imageCardView = binding.imageCardView
            )

            binding.imageCardView.setOnClickListener {
                clickListener.clickImage(item)
            }

            binding.userCardView.setOnClickListener {
                clickListener.clickUser(item)
            }
        }

    }
}