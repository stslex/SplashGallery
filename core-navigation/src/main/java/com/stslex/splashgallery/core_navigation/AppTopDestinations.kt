package com.stslex.splashgallery.core_navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest

sealed interface AppTopDestinations {

    val deepLink: String
    val arguments: List<String>
        get() = emptyList()

    val request: NavDeepLinkRequest
        get() = NavDeepLinkRequest.Builder
            .fromUri("$deepLink${arguments.joinToString("/", "/")}".toUri())
            .build()

    data class SingleCollection(
        private val transitionName: String,
        private val title: String
    ) : AppTopDestinations {

        override val deepLink: String
            get() = "app://nav_single_collection"

        override val arguments: List<String>
            get() = listOf(transitionName, title)
    }

    data class User(
        private val username: String
    ) : AppTopDestinations {

        override val deepLink: String
            get() = "app://nav_user"

        override val arguments: List<String>
            get() = listOf(username)
    }

    data class DetailsImage(
        private val id: String,
        private val url: String
    ) : AppTopDestinations {

        override val deepLink: String
            get() = "app://nav_details_photo"

        override val arguments: List<String>
            get() = listOf(id, url)
    }
}
