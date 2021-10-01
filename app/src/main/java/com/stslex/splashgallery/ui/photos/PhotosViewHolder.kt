package com.stslex.splashgallery.ui.photos

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.stslex.splashgallery.databinding.ItemRecyclerAllPhotosBinding
import com.stslex.splashgallery.ui.core.ClickListener
import com.stslex.splashgallery.ui.model.image.ImageModel
import com.stslex.splashgallery.utils.Resources.currentId


class PhotosViewHolder(
    private val binding: ItemRecyclerAllPhotosBinding,
    private val clickListener: ClickListener,
    private val coilListener: ImageRequest.Listener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageModel?) {
        with(binding) {
            if (item?.user?.username == currentId) {
                userCardView.visibility = View.GONE
            } else {
                avatarImageView.load(item?.user?.profile_image?.medium!!) {
                    placeholder(ColorDrawable(Color.GRAY))
                    transformations(CircleCropTransformation())
                }
                userCardView.visibility = View.VISIBLE
                usernameTextView.text = item.user.username
                userCardView.transitionName = item.user.username
                userCardView.setOnClickListener {
                    clickListener.clickUser(it)
                }
            }

            Log.i("TestPhotos::", "ViewHolder")
            imageImageView.load(item.urls.regular) {
                placeholder(ColorDrawable(Color.GRAY))
                transformations(RoundedCornersTransformation())
                listener(coilListener)
            }

            imageCardView.transitionName = item.id
            imageCardView.setOnClickListener {
                clickListener.clickImage(it, item.urls.regular)
            }
        }
    }
}