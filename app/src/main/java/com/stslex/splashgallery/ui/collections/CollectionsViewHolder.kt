package com.stslex.splashgallery.ui.collections

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.stslex.splashgallery.databinding.ItemRecyclerCollectionsBinding
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.ui.model.collection.CollectionModel
import com.stslex.splashgallery.utils.Resources.currentId
import com.stslex.splashgallery.utils.Resources.photos

class CollectionsViewHolder(
    private val binding: ItemRecyclerCollectionsBinding,
    private val clickListener: ClickListener,
    private val coilListener: ImageRequest.Listener
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: CollectionModel?) {
        with(binding) {
            if (item?.user?.username == currentId) {
                userCardView.visibility = View.GONE
            } else {
                userCardView.visibility = View.VISIBLE
                userImageView.load(item?.user?.profile_image?.medium!!) {
                    placeholder(ColorDrawable(Color.GRAY))
                    transformations(CircleCropTransformation())
                }
                usernameTextView.text = item.user.username
                userCardView.transitionName = item.user.username
                userCardView.setOnClickListener {
                    clickListener.clickUser(it)
                }
            }
            numberTextView.text = item.total_photos.toString() + photos
            titleTextView.text = item.title
            collectionImageView.load(item.cover_photo?.urls?.regular.toString()) {
                placeholder(ColorDrawable(Color.GRAY))
                transformations(RoundedCornersTransformation())
                listener(coilListener)
            }
            imageCardView.transitionName = item.id
            imageCardView.setOnClickListener {
                clickListener.clickImage(it, item.title)
            }
        }
    }
}