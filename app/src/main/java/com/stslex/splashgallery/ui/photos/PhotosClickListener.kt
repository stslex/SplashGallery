package com.stslex.splashgallery.ui.photos

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.google.android.material.card.MaterialCardView
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.main_screen.MainFragmentDirections
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragmentDirections
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.ref.WeakReference

@ExperimentalCoroutinesApi
class PhotosClickListener(
    private val parentFragment: WeakReference<Fragment>
) : OnClickListener {

    override fun clickImage(view: View, url: String) {
        val extras = FragmentNavigatorExtras(view to view.transitionName)
        val directions: NavDirections? = when (parentFragment.get()) {
            is MainFragment -> MainFragmentDirections.actionNavHomeToNavSinglePhoto(
                id = view.transitionName,
                url = url
            )
            is UserPhotosFragment, is UserLikesFragment -> UserFragmentDirections.actionNavUserToNavSinglePhoto(
                id = view.transitionName,
                url = url
            )
            is SingleCollectionFragment -> SingleCollectionFragmentDirections.actionNavSingleCollectionToNavSinglePhoto(
                id = view.transitionName,
                url = url
            )
            else -> null
        }
        directions?.navigate(view, extras)
    }

    override fun clickUser(view: MaterialCardView) {
        val extras = FragmentNavigatorExtras(view to view.transitionName)
        val directions: NavDirections? = when (parentFragment.get()) {
            is MainFragment -> MainFragmentDirections.actionNavHomeToNavUser(view.transitionName)
            is UserPhotosFragment, is UserLikesFragment -> UserFragmentDirections.actionNavUserSelf(
                username = view.transitionName
            )
            is SingleCollectionFragment -> SingleCollectionFragmentDirections.actionNavSingleCollectionToNavUser(
                username = view.transitionName
            )
            else -> null
        }
        directions?.navigate(view, extras)
    }

    private fun NavDirections.navigate(view: View, extras: Navigator.Extras) {
        Navigation.findNavController(view).navigate(this, extras)
    }
}