package com.stslex.splashgallery.ui.utils

fun String?.isNullCheck(): String = if (isNullOrEmpty()) "unknown" else this