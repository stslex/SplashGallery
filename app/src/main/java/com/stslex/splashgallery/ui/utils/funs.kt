package com.stslex.splashgallery.ui.utils

import com.stslex.splashgallery.ui.utils.AppResources.unknown

fun String?.isNullCheck(): String = if (isNullOrEmpty()) unknown else this