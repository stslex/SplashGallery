package com.stslex.splashgallery.utils

import com.stslex.splashgallery.utils.AppResources.unknown

fun String?.isNullCheck(): String = if (isNullOrEmpty()) unknown else this