package com.stslex.splashgallery.ui.detail_photo

import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras

class DetailPhotoClickListener(
    private val url: String
) : View.OnClickListener {

    override fun onClick(view: View) {
        val directions = PhotoDetailsFragmentDirections.actionNavSinglePhotoToNavSingleImage(
            id = view.transitionName,
            url = url
        )
        val extras = FragmentNavigatorExtras(view to view.transitionName)
        view.findNavController().navigate(directions, extras)
    }
}