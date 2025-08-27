package com.stslex.splashgallery.feature_collections.data

import com.stslex.splashgallery.core_coroutines.BuildConfig

object FirebaseConstants {

    /*API_KEY*/
    const val API_KEY = BuildConfig.API_KEY

    /*GET*/
    const val GET_COLLECTIONS = "collections"
    const val GET_USERS = "users"

    /*QUERY*/
    const val QUERY_API_KEY = "client_id"
    const val QUERY_PAGE = "page"
}