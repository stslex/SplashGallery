package com.stslex.splashgallery.ui.collections

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.stslex.splashgallery.ui.core.OnClickListener
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.main_screen.MainFragmentDirections
import com.stslex.splashgallery.ui.user.UserFragment
import com.stslex.splashgallery.ui.user.UserFragmentDirections
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.ref.WeakReference

@ExperimentalCoroutinesApi
class CollectionClickListener(
    private val parentFragment: WeakReference<Fragment>
) : OnClickListener {

    override fun clickImage(view: View, url: String) {
        val extras = FragmentNavigatorExtras(view to view.transitionName)
        val directions: NavDirections? = when (parentFragment.get()) {
            is MainFragment -> MainFragmentDirections.actionNavHomeToNavSingleCollection(
                transitionName = view.transitionName,
                title = url
            )
            is UserFragment -> UserFragmentDirections.actionNavUserToNavSingleCollection(
                transitionName = view.transitionName,
                title = url
            )
            else -> null
        }
        directions?.navigate(view, extras)
    }

    override fun clickUser(view: View) {
        val extras = FragmentNavigatorExtras(view to view.transitionName)
        val directions: NavDirections? = when (parentFragment.get()) {
            is MainFragment -> MainFragmentDirections.actionNavHomeToNavUser(
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