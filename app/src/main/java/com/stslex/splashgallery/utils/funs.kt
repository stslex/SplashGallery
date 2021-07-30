package com.stslex.splashgallery.utils

import android.content.Context
import com.stslex.splashgallery.GalleryApplication
import com.stslex.splashgallery.di.component.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is GalleryApplication -> appComponent
        else -> this.applicationContext.appComponent
    }