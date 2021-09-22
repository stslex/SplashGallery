package com.stslex.splashgallery.ui.user

import androidx.fragment.app.Fragment
import com.stslex.splashgallery.ui.core.AbstractView
import com.stslex.splashgallery.ui.user.pager.UserCollectionFragment
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import com.stslex.splashgallery.utils.Resources
import com.stslex.splashgallery.utils.SetImageWithGlide

interface UserUI {

    fun bind(
        glide: SetImageWithGlide,
        profileImageView: AbstractView.Image,
        totalCollectionsTextView: AbstractView.Text,
        totalLikesTextView: AbstractView.Text,
        totalPhotosTextView: AbstractView.Text,
        bioTextView: AbstractView.Text
    )

    fun getListOfTabs(): List<Fragment>

    data class Base(
        private val username: String,
        private val url: String,
        private val totalCollections: String,
        private val totalPhotos: String,
        private val totalLikes: String,
        private val bio: String
    ) : UserUI {
        override fun bind(
            glide: SetImageWithGlide,
            profileImageView: AbstractView.Image,
            totalCollectionsTextView: AbstractView.Text,
            totalLikesTextView: AbstractView.Text,
            totalPhotosTextView: AbstractView.Text,
            bioTextView: AbstractView.Text
        ) {
            glide.setImage(
                url,
                profileImageView.getImage(),
                needCrop = false,
                needCircleCrop = true
            )
            Resources.currentId = username
            totalCollectionsTextView.map(totalCollections)
            totalLikesTextView.map(totalLikes)
            totalPhotosTextView.map(totalPhotos)
            if (bio.isEmpty()) {
                bioTextView.hide()
            } else {
                bioTextView.show()
                bioTextView.map(bio)
            }
        }

        override fun getListOfTabs(): List<Fragment> = mapOf(
            totalPhotos.toInt() to UserPhotosFragment(),
            totalLikes.toInt() to UserLikesFragment(),
            totalCollections.toInt() to UserCollectionFragment()
        ).filter { it.key != 0 }.values.toList()

    }
}