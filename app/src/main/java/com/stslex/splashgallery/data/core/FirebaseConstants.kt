package com.stslex.splashgallery.data.core

import com.stslex.splashgallery.BuildConfig

object FirebaseConstants {

    /*API_KEY*/
    const val API_KEY = BuildConfig.API_KEY

    /*GET*/
    const val GET_PHOTOS = "photos"
    const val GET_COLLECTIONS = "collections"
    const val GET_USERS = "users"
    const val GET_DOWNLOAD = "download"
    const val GET_LIKES = "likes"

    /*QUERY*/
    const val QUERY_API_KEY = "client_id"
    const val QUERY_PAGE = "page"
    const val QUERY_PAGE_SIZE = "per_page"
}