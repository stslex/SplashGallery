package com.stslex.feature_collections.ui

import android.view.View
import androidx.navigation.findNavController
import com.stslex.core_navigation.AppTopDestinations
import com.stslex.core_ui.OnClickListener
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CollectionClickListener : OnClickListener {

    override fun clickImage(view: View, url: String) {
        val appDestination: AppTopDestinations = AppTopDestinations.SingleCollection(
            transitionName = view.transitionName,
            title = url
        )
        view.findNavController().navigate(appDestination.request)
    }

    override fun clickUser(view: View) {
        val appDestination: AppTopDestinations = AppTopDestinations.User(
            username = view.transitionName
        )
        view.findNavController().navigate(appDestination.request)
    }
}