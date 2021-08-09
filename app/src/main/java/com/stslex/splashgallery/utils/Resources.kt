package com.stslex.splashgallery.utils

import androidx.fragment.app.Fragment
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.user.pager.UserLikesFragment
import com.stslex.splashgallery.ui.user.pager.UserPhotosFragment
import java.io.File

object Resources {
    lateinit var photos: String
    lateinit var collections: String
    lateinit var likes: String
    lateinit var cache: File
    lateinit var unknown: String

    val scrollState: MutableMap<Fragment, Int> = mutableMapOf(
        MainFragment() to 0,
        UserPhotosFragment() to 0,
        UserLikesFragment() to 0,
        SingleCollectionFragment() to 0
    )
}
