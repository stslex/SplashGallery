package com.stslex.splashgallery.ui.photos

import android.view.View
import androidx.navigation.findNavController
import com.stslex.core_navigation.AppTopDestinations
import com.stslex.core_ui.OnClickListener
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class PhotosClickListener : OnClickListener {

    override fun clickImage(view: View, url: String) {
        val appTopDestination: AppTopDestinations = AppTopDestinations.DetailsImage(
            id = view.transitionName,
            url = URLEncoder.encode(url, StandardCharsets.UTF_8.displayName())
        )
        view.findNavController().navigate(appTopDestination.request)
    }

    override fun clickUser(view: View) {
        val appTopDestination: AppTopDestinations = AppTopDestinations.User(view.transitionName)
        view.findNavController().navigate(appTopDestination.request)
    }
}