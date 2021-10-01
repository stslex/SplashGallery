package com.stslex.splashgallery.utils

import com.stslex.splashgallery.utils.Resources.unknown

fun String?.isNullCheck(): String = if (isNullOrEmpty()) unknown else this
