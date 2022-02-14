package com.stslex.splashgallery.ui.photos

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.main_screen.MainFragmentDirections
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragmentDirections
import com.stslex.splashgallery.ui.user.UserFragmentDirections

class PhotosClickListener(private val parentFragment: PhotosEnumFragments) : OnClickListener {

    override fun clickImage(view: View, url: String) {
        val directions: NavDirections = when (parentFragment) {
            PhotosEnumFragments.MainFragment -> MainFragmentDirections.actionNavHomeToNavSinglePhoto(
                id = view.transitionName,
                url = url
            )
            PhotosEnumFragments.SingleCollectionFragment -> SingleCollectionFragmentDirections.actionNavSingleCollectionToNavSinglePhoto(
                id = view.transitionName,
                url = url
            )
            PhotosEnumFragments.UserPhotosFragment, PhotosEnumFragments.UserLikesFragment -> UserFragmentDirections.actionNavUserToNavSinglePhoto(
                id = view.transitionName,
                url = url
            )
        }
        directions.navigate(view)
    }

    override fun clickUser(view: View) {
        val directions: NavDirections = when (parentFragment) {
            PhotosEnumFragments.MainFragment -> MainFragmentDirections.actionNavHomeToNavUser(view.transitionName)
            PhotosEnumFragments.SingleCollectionFragment -> SingleCollectionFragmentDirections.actionNavSingleCollectionToNavUser(
                username = view.transitionName
            )
            PhotosEnumFragments.UserPhotosFragment, PhotosEnumFragments.UserLikesFragment -> UserFragmentDirections.actionNavUserSelf(
                username = view.transitionName
            )
        }
        directions.navigate(view)
    }

    private fun NavDirections.navigate(view: View) {
        val extras = FragmentNavigatorExtras(view to view.transitionName)
        Navigation.findNavController(view).navigate(this, extras)
    }
}